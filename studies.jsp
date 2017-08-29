<%--
        Document: aboutl.jsp
        Created On: Feb 4, 2016
        Authors: Deepak Rohan, Abhishek

--%>
<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>
<%-- Code to display Page Name --%>
<h3 id="page_name">My Studies</h3>
<%-- Code to add new study   --%>
<h3 id="add_new_study"><a href="newstudy.jsp" >Add a new study</a></h3>
<%-- Code to go Back to the Main Page  --%>
<a href="main.jsp" id="back_to_page">&laquo;Back to the Main Page</a>
<%-- Section to display studies details --%> 
<%-- Clicking on Start, Stop to Participate in that study and  Edit button to display edit page and edit study details in it--%>
<section >

    <div class="table-responsive">
        <table class="table" >
            <tr>
                <th>Study Name</th>
                <th>Requested Participants</th>     
                <th>Participations</th>
                <th>Status</th>
                <th>Action</th>
                <th>Social Media Share</th>
            </tr>
            <c:forEach var="study" items="${myStudies}">
                <tr>
                    <td><c:out value="${study.studyName}"/></td>
                    <td><c:out value="${study.reqParticipants}"/></td>
                    <td><c:out value="${study.actParticipants}"/></td> 
                    <c:if test="${study.SStatus == 'Open'}">
                        <td>
                            <form action="studies" method="post">
                                <input type="hidden" name="action" value="stop" />
                                <input type="hidden" name="studyID" value="${study.studyID}" />
                                <button type="submit" class="btn btn-primary">Started</button>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${study.SStatus == 'Closed'}">
                        <td>
                            <form action="studies" method="post">
                                <input type="hidden" name="action" value="start" />
                                <input type="hidden" name="studyID" value="${study.studyID}" />
                                <button type="submit" class="btn btn-primary">Stopped</button>
                            </form>
                        </td>
                    </c:if>
                    <td>
                        <form action="studies" method="post">
                            <input type="hidden" name="action" value="edit" />
                            <input type="hidden" name="studyID" value="${study.studyID}" />
                            <button type="submit" class="btn btn-primary">Edit</button>
                        </form>
                    </td>
                                        <td>
                        <iframe 
                            src="https://www.facebook.com/plugins/share_button.php?href=http://abhishekasg4-abhisheknbad.rhcloud.com/Abhishek_Assignment4/&layout=button_count&mobile_iframe=true&width=96&height=20&appId" width="96" height="20" style="border:none;overflow:hidden" scrolling="no" frameborder="0" allowTransparency="true">
                        </iframe>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <label>*Click on the status to modify it.</label>
    </div>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>