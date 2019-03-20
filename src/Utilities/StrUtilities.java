package Utilities;

/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
public class StrUtilities
{

    /**
     * <code>\u000a</code> linefeed LF ('\n').
     *
     * @see
     * <a href="http://java.sun.com/docs/books/jls/third_edition/html/lexical.html#101089">JLF:
     * Escape Sequences for Character and String Literals</a>
     * @since 2.2
     */
    public static final char LF = '\n';

    /**
     * <code>\u000d</code> carriage return CR ('\r').
     *
     * @see
     * <a href="http://java.sun.com/docs/books/jls/third_edition/html/lexical.html#101089">JLF:
     * Escape Sequences for Character and String Literals</a>
     * @since 2.2
     */
    public static final char CR = '\r';

    /**
     * Removes one newline from end of a String if it's there, otherwise leave
     * it alone. A newline is &quot;<code>\n</code>&quot;,
     * &quot;<code>\r</code>&quot;, or &quot;<code>\r\n</code>&quot;.
     *
     * NOTE: This method changed in 2.0. It now more closely matches Perl chomp.
     *
     * <pre>
     * StringUtils.chomp(null)          = null
     * StringUtils.chomp("")            = ""
     * StringUtils.chomp("abc \r")      = "abc "
     * StringUtils.chomp("abc\n")       = "abc"
     * StringUtils.chomp("abc\r\n")     = "abc"
     * StringUtils.chomp("abc\r\n\r\n") = "abc\r\n"
     * StringUtils.chomp("abc\n\r")     = "abc\n"
     * StringUtils.chomp("abc\n\rabc")  = "abc\n\rabc"
     * StringUtils.chomp("\r")          = ""
     * StringUtils.chomp("\n")          = ""
     * StringUtils.chomp("\r\n")        = ""
     * </pre>
     *
     * @param str the String to chomp a newline from, may be null
     * @return String without newline, <code>null</code> if null String input
     */
    public static String chomp(String str)
    {
        if (isEmpty(str))
        {
            return str;
        }

        if (str.length() == 1)
        {
            char ch = str.charAt(0);
            if (ch == CR || ch == LF)
            {
                return "";
            }
            return str;
        }

        int lastIdx = str.length() - 1;
        char last = str.charAt(lastIdx);

        if (last == LF)
        {
            if (str.charAt(lastIdx - 1) == CR)
            {
                lastIdx--;
            }
        } else if (last != CR)
        {
            lastIdx++;
        }
        return str.substring(0, lastIdx);
    }
    // Empty checks
    //-----------------------------------------------------------------------

    /**
     * Checks if a String is empty ("") or null.
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * NOTE: This method changed in Lang version 2.0. It no longer trims the
     * String. That functionality is available in isBlank().
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str)
    {
        return str == null || str.length() == 0;
    }

    /**
     * Author https://stackoverflow.com/questions/27095380/convert-string-to-2d-char-array - Matthew Sant
     * @param rows
     * @param cols
     * @return 
     */
    public static char[][] StringTo2D_CharArray(String map, int rows, int cols)
    {
        
        String[] components = map.split("\n");
        char[][] chars = new char[components.length + 1][];
        for(int i = 0; i < components.length; i++)
        {
            String component = components[i];
            chars[i] = component.toCharArray();
        }
        chars[components.length] = new char[] { '\0' };
        
        return chars;
        /*
        int offset = 0;
        System.out.println("--");
        char[][] res = new char[rows][cols];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < rows; j++)
            {
                res[i][j] = map.charAt(j + i*cols);
                System.out.print(res[i][j]);
            }
            System.out.println();
        }
        return res;
*/
    }
}
