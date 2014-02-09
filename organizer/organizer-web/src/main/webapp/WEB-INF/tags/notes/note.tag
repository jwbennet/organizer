<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/notes/tldHeader.jsp"%>

<%@ attribute name="note" required="true" type="com.bbtech.organizer.server.entities.Note" %>

			<li id="note-${note.id}" class="note-container">
				<div id="display-note-${note.id}" data-role="none" style="width: 50%; float: left;">
					${note.wikiText}
				</div>
				<div data-role="controlgroup" data-type="horizontal" data-mini="true" class="ui-li-aside">
		           	<a href="#" data-role="button" data-icon="pencil" data-iconpos="notext" onclick="editNote(${note.id})">Edit</a>
            		<a href="#note-delete-confirm-dialog" data-rel="dialog" data-role="button" data-icon="delete" data-iconpos="notext" onclick="confirmDeleteNote(${note.id})">Delete</a>
        		</div>
        		<br style="clear: both;" />
			</li>
