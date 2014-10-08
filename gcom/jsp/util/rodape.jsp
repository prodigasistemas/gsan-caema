<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<logic:notPresent scope="session" name="origemGIS">

<table width="770" cellspacing="4" cellpadding="0">
	<tr>
    	<td>
      	<table width="100%" cellpadding="2" class="footer">
        	<tr>
          		<td  align="left"> 
	          		<logic:present scope="application" name="versaoDataBase"> 
					Banco: ${applicationScope.versaoDataBase}
					</logic:present>  
					<logic:notPresent scope="application" name="versaoDataBase"> 
					PMSS
					</logic:notPresent>
				</td>
          		<td align="right">Vers&atilde;o: 9.2.7.1.2o (${applicationScope.versaoTipo}) 23/07/2014 - 16:56:13 </td>
        	</tr>
      	</table>
		</td>
  	</tr>
</table>
</logic:notPresent>