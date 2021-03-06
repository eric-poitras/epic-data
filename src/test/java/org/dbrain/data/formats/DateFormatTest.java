/*
 * Copyright [2014] [Eric Poitras]
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

package org.dbrain.data.formats;

import junit.framework.Assert;
import org.dbrain.data.text.LocalDateFormat;
import org.dbrain.data.text.ParseException;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: epoitras
 * Date: 17/04/13
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateFormatTest {

    /**
     * Parse a simple value and check the result.b
     */
    @Test
    public void testParse() throws Exception {
        LocalDateFormat df = new LocalDateFormat( "yyyy-MM-DD", Locale.ENGLISH );

        LocalDate testDate = df.parse( "2012-01-01" );
        LocalDate expectedDate = LocalDate.of( 2012, 1, 1 );

        Assert.assertEquals( expectedDate, testDate );

    }

    /**
     * Do not expect the parser to be lenient.
     */
    @Test( expected = ParseException.class )
    public void testParseNonLenient() throws Exception {
        LocalDateFormat df = new LocalDateFormat( "yyyy-MM-DD", Locale.ENGLISH );
        LocalDate testDate = df.parse( "2012-01-32" );
    }

    @Test
    public void testFormatParse() throws Exception {
        LocalDateFormat df = new LocalDateFormat( "yyyy-MM-DD", Locale.ENGLISH );
        LocalDate testDate = df.parse( "2012-01-01" );
        String testDateString = df.format( testDate );

        Assert.assertEquals( testDateString, "2012-01-01" );


    }
}
