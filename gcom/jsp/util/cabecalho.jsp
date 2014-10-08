<link REL="SHORTCUT ICON" HREF="<bean:message key="caminho.imagens"/>icoGSAN.ico"> 
<script>
function setCookie(c_name,value,exdays)
{
var exdate=new Date();
exdate.setDate(exdate.getDate() + exdays);
var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());
document.cookie=c_name + "=" + c_value;
}

function getCookie(c_name)
{
var i,x,y,ARRcookies=document.cookie.split(";");
for (i=0;i<ARRcookies.length;i++)
{
  x=ARRcookies[i].substr(0,ARRcookies[i].indexOf("="));
  y=ARRcookies[i].substr(ARRcookies[i].indexOf("=")+1);
  x=x.replace(/^\s+|\s+$/g,"");
  if (x==c_name)
    {
    return unescape(y);
    }
  }
}

function delete_cookie ( cookie_name )
{
  var cookie_date = new Date ( );  // current date & time
  cookie_date.setTime ( cookie_date.getTime() - 1 );
  document.cookie = cookie_name += "=; expires=" + cookie_date.toGMTString();
}
</script>

<logic:notPresent scope="session" name="origemGIS">
	<body alink="black" vlink="black">
		<table width="770" border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td height="0" valign="top" class="topstrip">
				<table width="100%" 
					height="0" 
					border="0" 
					cellpadding="0"
					cellspacing="0">
					
					<tr>
						<td height="0" 
							valign="bottom">
							<img src="${applicationScope.logoMarca}">
						</td>
		
						<td width="35%" 
							align="center">
							
							<br>
							
							<marquee bgcolor="#CBE5FE"
								title="titulo" 
								valign="top" 
								loop="true" 
								scrollamount="4"
								behavior="scroll" 
								direction="left">
								<font color="black">
									<strong>${requestScope.mensagemAviso} </strong>
								</font>
							</marquee>
							
					<%	if (!getServletContext().getAttribute("nomeEmpresa").equals("IPAD")){ %>
							<a href="http://xwiki.ipad.com.br/" style="text-decoration: none;" target="_blank">
								<img src="<bean:message key="caminho.imagens"/>ajuda2.gif" border="0">
							</a>
		    		<%	} else {	%>
							<img src="<bean:message key="caminho.imagens"/>ajuda2.gif" border="0">
		    		<%	}	%>
						</td>
						
						<td align="right" valign="bottom">
							<img src="<bean:message key="caminho.imagens"/>logo_menu_superior.gif" border="0">
						</td>
						
					</tr>
					
							<table cellpadding="0" cellspacing="0" border="0" class="layerCaminhoMenu">
				
								<tr>
								    <logic:present name="caminhoMenuFuncionalidade">
									   <td><font size="1">${sessionScope.caminhoMenuFuncionalidade}</font></td>
									</logic:present>
				</table>
				</td>
			</tr>
		</table>
	</body>
</logic:notPresent>


<script>
<!--

function backButtonOverride()
{
	var desativaHistoryBack = getCookie("desativaHistoryBack");
  if (desativaHistoryBack != null && desativaHistoryBack != "")
  {
  
	  delete_cookie("desativaHistoryBack");
  }else{

	  setTimeout("backButtonOverrideBody()", 1);
	  
  }

}

function backButtonOverrideBody()
{

  try {
	
  //  history.forward();

  } catch (e) {
    // OK to ignore
  }
 
  setTimeout("backButtonOverride()", 500);
}
-->
</script>

<script>
backButtonOverride();
</script>

