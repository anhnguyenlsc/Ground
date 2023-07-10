package com.ground.data.models.supports;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicLong;

@UtilityClass
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class Snowflake_ {

// long = 64bit

static final AtomicLong TIMESTAMP = new AtomicLong(0L);

	/*
	 * 		var epoch = LocalDateTime.of(2020, 1, 1, 0, 0).toInstant(ZoneOffset.UTC).toEpochMilli();
		var sf = Instant.now().toEpochMilli() - epoch;
		var cur = sf << 22;
		var reverse = cur >> 22;
		var now = reverse + epoch;
	 */

//var sf = Instant.now().toEpochMilli() - epoch;
static final AtomicLong SEQUENCE = new AtomicLong(0L);
private static final long epoch = LocalDateTime.of(2020, 1, 1, 0, 0).toInstant(ZoneOffset.UTC).toEpochMilli();
long FILL = ~(0L & 0);
byte TIMESTAMP_BITS = 41;
byte HARDWARE_BITS = 10;
byte SEQUENCE_BITS = 12;
long HARDWARE_MASK = (FILL >>> TIMESTAMP_BITS) << SEQUENCE_BITS;
long SEQUENCE_MASK = FILL >>> (TIMESTAMP_BITS + HARDWARE_BITS);
long TIMESTAMP_MASK = FILL << (HARDWARE_BITS + SEQUENCE_BITS);
long MAX = FILL >>> TIMESTAMP_BITS;
long CYCLE = 4096; // 2^12 = 4096

public static long generate(long address) {

    //log.debug("fill------------>{}",Long.toBinaryString(FILL));
    //log.debug("HARDWARE_MASK--->{}",Long.toBinaryString(HARDWARE_MASK));
    //log.debug("SEQUENCE_MASK--->{}",Long.toBinaryString(SEQUENCE_MASK));
    //log.debug("TIMESTAMP_MAS--->{}",Long.toBinaryString(TIMESTAMP_MASK));


    long timestamp = ((Instant.now().toEpochMilli() - epoch) << (HARDWARE_BITS + SEQUENCE_BITS)) & TIMESTAMP_MASK;
    //log.debug("timestamp------------>{}",Long.toBinaryString(Instant.now().toEpochMilli() - epoch));
    //log.debug("timestamp------------>{}",Long.toBinaryString(timestamp));
    long hardware = (address << SEQUENCE_BITS) & HARDWARE_MASK;
    //log.debug("hardware------------>{}",Long.toBinaryString(address));
    //log.debug("hardware------------>{}",Long.toBinaryString(hardware));
    long sequence = 0L;
    //log.debug("timestamp------------>{} -> {}",CURRENTS.get(),timestamp);

    if (TIMESTAMP.get() == timestamp) {
        sequence = SEQUENCE.incrementAndGet();
    } else {
        SEQUENCE.set(sequence);
        TIMESTAMP.set(timestamp);
    }


    //log.debug("sequence------------>{} -> {}",SEQUENCE.get(),sequence);

    sequence = sequence & SEQUENCE_MASK;

    log.debug("Snowflake -------------------->>{} : {},{}",timestamp | hardware | sequence,timestamp,sequence);
    return timestamp | hardware | sequence;
}

public static Instant toInstant(long sf) {
    long timestamp = (sf >>> (HARDWARE_BITS + SEQUENCE_BITS)) + epoch;
    return Instant.ofEpochMilli(timestamp);
}

public static long fromSnowflake(Instant instant) {
    long timestamp = (instant.toEpochMilli() - epoch) << (HARDWARE_BITS + SEQUENCE_BITS);
    return timestamp;
}

public static long toSnowflake(Instant instant) {
    long timestamp = (instant.toEpochMilli() - epoch) << (HARDWARE_BITS + SEQUENCE_BITS);
    return timestamp | MAX;
}

}