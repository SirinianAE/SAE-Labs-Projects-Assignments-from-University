#!/bin/sh

LC_NUMERIC=en_US.UTF-8
export LC_NUMERIC

INPUTFILE=lab2.tr

if [ ! -f "$INPUTFILE" ]; then
	echo "ERROR: Can't find input file $INPUTFILE"
	exit 1
elif [ ! -s "$INPUTFILE" ]; then
	echo "ERROR: Empty input file $INPUTFILE"
	exit 1
fi

cat $INPUTFILE | gawk '
	BEGIN{ sign = -1; }
	{
		if ($1=="+") { sign = "0+"; }
		if( $1=="-" ){ sign = "1-"; }
		if ($1=="d") { sign = "2d"; }
		if ($1=="r") { sign = "3r"; }
		printf("%d %f %s %d %d %d\n", 1000 + $12, $2, sign, $3, $4, $6);
	}' | sort | gawk '
	BEGIN{
		generated = serviced = dropped = qcounter1 = qcounter2 = dropped2 = mcount1 = mcount2 = 0; 
		qdelay = mean_delay = final_mdelay = final_qdelay = 0.0;
		prev_packet = prev_qdelay = prev_mdelay = prev_sign = -1;
	}
	{
		if ($3 == "0+" && ($4 == 0 || $4 == 1)) { generated++; }
		if ($3 == "2d" && $4 == 2) { dropped++; }
		if ($3 == "2d" && $4 != 2) { dropped2++; }
		if ($3 == "3r" && $5 == 3) { serviced++; }
		if ($4 == 2 && $3 == "0+") { qcounter1++; }
		if ($4 == 2 && $3 == "1-") { qcounter2++; }

		if (prev_packet != $1) {
			if ($3 == "0+" && ($4 == 0 || $4 == 1)) { prev_mdelay = -$2; }
		}
		
		if (prev_packet == $1) {
			if ($3 == "0+" && $4 == 2) { prev_qdelay = -$2; }
			if ($3 == "2d") { prev_mdelay = prev_qdelay = 0.0; }
			if ($3 == "1-" && $4 ==2 ) {
				if (prev_qdelay != 0) {
					prev_qdelay += $2;
					qdelay += prev_qdelay; 
					mcount2++;
				}
				prev_qdelay = 0.0;
			}
			if ($3 == "3r" && $5 == 3) {
				if (prev_mdelay != 0) {
					prev_mdelay += $2;
					mean_delay += prev_mdelay; 
					mcount1++;
				};
				prev_mdelay = 0.0; 
			}
		}
		prev_packet = $1;
	}
	END{
		print "Generated Packets			:",generated;
		print "Serviced Packets			:", serviced;
		print "Dropped	in queue 2-3			:",dropped;
		print "Dropped	in other queues			:",dropped2;
#		print "In queue	of node 2		:", qcounter1-qcounter2-dropped;
		print "Delivery Ratio (%)			:", 100*serviced/(serviced+dropped+dropped2);

		if (mcount1 != 0) { final_mdelay = mean_delay / mcount1; } else { final_mdelay = 0.0; }
		if (mcount2 != 0) { final_qdelay = qdelay / mcount2; } else { final_qdelay = 0.0; }
		print "Mean packet delay (secs)		:", final_mdelay;
		print "Mean queueing delay in queue 2-3 (secs)	:", final_qdelay;
	}'

cat $INPUTFILE | gawk '
	BEGIN{ sign = -1; }
	{
		if ($1=="+") { sign = "0+"; }
		if( $1=="-" ){ sign = "1-"; }
		if ($1=="d") { sign = "2d"; }
		if ($1=="r") { sign = "3r"; }
		printf("%f %d %s %d %d %d\n", $2, 1000 + $12, sign, $3, $4, $6);
	}' | sort | gawk '
	BEGIN{
		counter = tot_packets = 0; 
		tot_time = u_time = prev_time = end_time = link_util = 0.0;
		start_time = 1000000.0;
	}
	{
		if ($3 == "1-" && $4 == 2) { if (start_time > $1) { start_time = $1; } }
		if ($3 == "3r" && $5 == 3) { if (end_time < $1) { end_time = $1; } }
		
		if ($3 == "1-" && $4 == 2) {
			tot_packets++;
			counter++;
			if (counter == 1 && tot_packets > 1) {
				u_time += $1 - prev_time;
			}
		}

		if ($3 == "3r" && $5 == 3) {
			counter--;
			if (counter == 0) {
				prev_time = $1; 
			} else {
				prev_time = 0.0; 
			}
		}
	}
	END{
		if (tot_packets > 0) {
			tot_time = end_time - start_time;
			u_time = tot_time - u_time;
			link_util = 100.0 * u_time / tot_time;
		} else {
			link_util = 0.0;
		}
		print "Link utilization (%)			:", link_util;
	}'

