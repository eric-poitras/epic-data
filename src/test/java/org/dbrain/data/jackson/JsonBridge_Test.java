/*
 * Copyright [2015] [Eric Poitras]
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package org.dbrain.data.jackson;

import junit.framework.Assert;
import org.dbrain.data.Value;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by epoitras on 06/01/15.
 */
public class JsonBridge_Test {

    org.dbrain.data.impl.value.json.JsonBridge mapper;


    @Before
    public void setUp() throws Exception {
        mapper = org.dbrain.data.impl.value.json.JsonBridge.get();

    }

    @Test
    public void testSerializeLong() throws Exception {
        String s1 = mapper.writeToString( new Long( 10 ) );
        String s2 = mapper.writeToString( new Long( 999999999999999l ) );
        String s3 = mapper.writeToString( new Long( 1000000000000000l ) );
        String s4 = mapper.writeToString( new Long( -999999999999999l ) );
        String s5 = mapper.writeToString( new Long( -1000000000000000l ) );

        Assert.assertEquals( "10", s1 );
        Assert.assertEquals( "999999999999999", s2 );
        Assert.assertEquals( "\"1000000000000000\"", s3 );
        Assert.assertEquals( "-999999999999999", s4 );
        Assert.assertEquals( "\"-1000000000000000\"", s5 );

    }

    @Test
    public void testSerializeBigInteger() throws Exception {
        String s1 = mapper.writeToString( new BigInteger( "10" ) );
        String s2 = mapper.writeToString( new BigInteger( "999999999999999" ) );
        String s3 = mapper.writeToString( new BigInteger( "1000000000000000" ) );
        String s4 = mapper.writeToString( new BigInteger( "-999999999999999" ) );
        String s5 = mapper.writeToString( new BigInteger( "-1000000000000000" ) );

        Assert.assertEquals( "10", s1 );
        Assert.assertEquals( "999999999999999", s2 );
        Assert.assertEquals( "\"1000000000000000\"", s3 );
        Assert.assertEquals( "-999999999999999", s4 );
        Assert.assertEquals( "\"-1000000000000000\"", s5 );

    }

    @Test
    public void testSerializeBigDecimal() throws Exception {
        String s1 = mapper.writeToString( new BigDecimal( "10" ) );
        String s2 = mapper.writeToString( new BigDecimal( "999999999999999" ) );
        String s3 = mapper.writeToString( new BigDecimal( "1000000000000000" ) );
        String s4 = mapper.writeToString( new BigDecimal( "99999999999.9999" ) );
        String s5 = mapper.writeToString( new BigDecimal( "100000000000.1234" ) );
        String s6 = mapper.writeToString( new BigDecimal( "-999999999999999" ) );
        String s7 = mapper.writeToString( new BigDecimal( "-1000000000000000" ) );
        String s8 = mapper.writeToString( new BigDecimal( "-99999999999.9999" ) );
        String s9 = mapper.writeToString( new BigDecimal( "-100000000000.1234" ) );

        Assert.assertEquals( "10", s1 );
        Assert.assertEquals( "999999999999999", s2 );
        Assert.assertEquals( "\"1000000000000000\"", s3 );
        Assert.assertEquals( "99999999999.9999", s4 );
        Assert.assertEquals( "\"100000000000.1234\"", s5 );
        Assert.assertEquals( "-999999999999999", s6 );
        Assert.assertEquals( "\"-1000000000000000\"", s7 );
        Assert.assertEquals( "-99999999999.9999", s8 );
        Assert.assertEquals( "\"-100000000000.1234\"", s9 );

    }



    @Test
    public void testSerializeInteger() throws Exception {
        String s = mapper.writeToString( new Integer( 10 ) );
        Assert.assertEquals( "10", s );
    }

    @Test
    public void testSerializeObject() throws Exception {
        String s = mapper.writeToString( new TestLongClass() );
        TestLongClass tlc = mapper.parseObject( s, TestLongClass.class );
        Assert.assertNotNull( tlc );
    }

    @Test
    public void testObjectToValue() throws Exception {
        TestLongClass tlc = new TestLongClass();
        Value.Map v = mapper.objectToValue( tlc ).getMap();

        Assert.assertNotNull( v );
        Assert.assertEquals( tlc.getBoxedBigDecimal().longValue(), v.getLong( "boxedBigDecimal" ).longValue() );
    }
}