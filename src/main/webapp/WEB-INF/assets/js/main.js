var INFO = '#list';
var DETAILS = '#details';
var BACK = '#back';
var HEADER = '#header';

$(function() {
	var infoBox = $('#info-label');
	infoBox.hide();
	$(BACK).hide();
	$('a.seriesDetails').click(function() {
		var seriesUrl = $(this).attr('href');
		var seriesName = $(this).text();
		infoBox.text('loading details ...').show();
		$.ajax({
			url : seriesUrl,
			success : function(data) {
				$(INFO).hide();
				$(BACK).show();
				$(DETAILS).append($(data));
				infoBox.hide();
				$(HEADER).text(seriesName);
				$('#nav-seasons').affix({
					offset : {
						top: $('.page-header').height() + 50
					}
				});
				
				// $('body').scrollspy({ target: '#nav-seasons ul.nav' });
			},
			error: function() {
				infoBox.text("error");
			}
		});
		return false;
	});
	
	$(BACK).click(function() {
		$(DETAILS).empty();
		$(INFO).show();
		$(BACK).hide();
		$(HEADER).text("Epiguider");
	});
	
	$('a.modal-form-submit').click(function(e) {
		// find form and submit it
		$(this).parent().parent().find('form').submit();
		return false;
	});
	
	$('#showSearch').keyup(function() {
		var value = $(this).val().toLowerCase();
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