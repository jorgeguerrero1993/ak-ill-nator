<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match='/'>
  <html> 
     <head><title>AKILLNATOR</title></head>
     <body>

        <h2> Patient </h2>
         <table border = "1">
         
	    <tr bgcolor = "skyblue">
 		<th> Name </th>
		<th> birthDate </th>
                <th> Gender </th>
                <th> Weight </th>
	    </tr>
     
      <xsl:for-each select="Patient">
      <xsl:sort select="@name" />
         
<<<<<<< HEAD
            
           <tr>
    <td><xsl:value-of select="@name"/></td>
    <td><xsl:value-of select="@birthDate"/></td>
    <td><xsl:value-of select="@gender"/></td>
     <td><xsl:value-of select="@weight"/></td>
  </tr>
=======
            <tr>
            <td><i><xsl:value-of select="@name" /></i></td>
            <td><xsl:value-of select="@birthDate" /></td>
            <td><xsl:value-of select="@gender" /></td>
            <td><xsl:value-of select="@weight" /></td>
            </tr>
>>>>>>> branch 'master' of https://github.com/jorgeguerrero1993/ak-ill-nator
         
      </xsl:for-each>   
     
   
       </table>
     </body>
     </html>
 </xsl:template>
</xsl:stylesheet>