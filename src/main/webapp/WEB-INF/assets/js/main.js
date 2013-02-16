var INFO = '#list';
var DETAILS = '#details';
var BACK = '#back';

$(function() {
	var infoBox = $('#info-label');
	infoBox.hide();
	var backLinkImage = $(BACK + ' i');
	$('a.seriesDetails').click(function() {
		var seriesUrl = $(this).attr('href');
		infoBox.text('loading details ...').show();
		$.ajax({
			url : seriesUrl,
			success : function(data) {
				$(INFO).hide();
				$(DETAILS).append($(data));
				infoBox.hide();
				backLinkImage.attr('class', 'icon-arrow-left');
				$('#season-menu').tab();
			}
		});
		return false;
	});
	
	$(BACK).click(function() {
		$(DETAILS).empty();
		$(INFO).show();
		backLinkImage.attr('class', 'icon-arrow-left-white');
	});
	
	$('a.modal-form-submit').click(function(e) {
	    // find form and submit it
	    $(this).parent().parent().find('form').submit();
	    return false;
	});
	
	$('#showSearch').keyup(function(){ 
		var value = $(this).attr("value").toLowerCase();
	    if (value == "") {
	      $("#shows li").show();
	    } else {
	      $("#shows li").each(function() {
	        var id = $(this).data('name').toLowerCase();
	        if (id.indexOf(value) == -1) {
	          $(this).slideUp('slow');
	        } else {
	          $(this).slideDown('slow');
	        }
	      });
	    }
	});
});