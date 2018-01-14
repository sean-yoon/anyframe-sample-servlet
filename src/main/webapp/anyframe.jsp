<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/sample/common/top.jsp"%>
	</div>
    <hr />
<script type="text/javascript">
	function fncUploadMovie() {
	    document.movieForm.action="<c:url value='/nonblocking'/>";
	    document.movieForm.submit();
	}
</script> 
  	<div id="container">
    	<div class="main_greeting">
        	<dl>
                <dt>Welcome to Anyframe 5.5.1</dt>
                <dd>Congratulations! Anyframe application has been successfully installed. Anyframe is an open source project and application framework that provides basic architecture, common technical services, templates to help you develop web applications on the Java platform quickly and efficiently.</dd>
            </dl>
        </div>
        <hr />

        <div class="itemBox_1">
        	<table summary="This is a list of installed plugins">
            <caption>A list of installed plugins</caption>
            	<colgroup>
                	<col style="width:230px;" />
                    <col style="" />
                </colgroup>
            	<tr>
                	<th>Installed Plugins</th>
                    <td>
                    <form:form name="movieForm" method="post" enctype="multipart/form-data">
						<input type="hidden" name="contents" value="12345678901234567890" />
                    	<ul><!--<input type="file" name="fileName"><input type="submit" value="Upload">-->
                            <li><input type="file" name="fileName" /><a href="javascript:fncUploadMovie();">Servlet 3.1</a></li>
                            <!--Add new configuration here-->
<!--logging-configuration-START-->
<li>Logging 1.0.3</li>
<!--logging-configuration-END-->
<!--spring-configuration-START-->
<li>Spring 1.0.3</li>
<!--spring-configuration-END-->
<!--datasource-configuration-START-->
<li>Datasource 1.0.3</li>
<!--datasource-configuration-END-->
                        </ul>
                    </form:form>
                    </td>
                </tr>
            </table>
        </div>
        <hr />
        
        <div class="itemBox_1 itemBox_2">
        	<table summary="This is a list of generated CRUD codes">
            <caption>A list of generated CRUD codes</caption>
            	<colgroup>
                	<col style="width:230px;" />
                    <col style="" />
                </colgroup>
            	<tr>
                	<th>Generated CRUD Codes</th>
                    <td>
                    	<ul>
                            <!--Add new crud generation menu here-->
                        </ul>
                    </td>
                </tr>
            </table>
        </div>

    </div>
    <hr />
<%@ include file="/sample/common/bottom.jsp"%>