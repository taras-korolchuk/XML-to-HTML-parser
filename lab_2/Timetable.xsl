<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/timetable">
        <html>
            <body>
                <table border="1">
                    <tr>
                        <th>Name</th>
                        <th>Faculty</th>
                        <th>Cathedra</th>
                        <th>ClassNumber</th>
                        <th>Students</th>
                    </tr>
                    <xsl:for-each select="teacher">
                        <tr>
                            <td>
                                <xsl:value-of select="name" />
                            </td>
                            <td>
                                <xsl:value-of select="faculty" />
                            </td>
                            <td>
                                <xsl:value-of select="cathedra" />
                            </td>
                            <td>
                                <xsl:value-of select="classNumber" />
                            </td>
                            <td>
                                <xsl:value-of select="students" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>