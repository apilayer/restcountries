var restc = restc || {};

$(function () {
	'use strict';
	
	restc.ui = {
		navbarlink 	: $('.rc-navbar-link'),
		tryit 		: $('.rc-a-tryit'),
		inputTryit 	: $('.rc-input-tryit'),
		jsonwell 	: $('#rc-json-well'),
		warning 	: $('#rc-warning'),
		errorMsg	: $('#rc-error')
	};
	
	restc.ui.navbarlink.click(function(e) {
		navbarLinkActive(e.target);
	});
	
	restc.ui.tryit.click(function(e) {
		ajax($(e.target).parent().prev().val(), function(data) {
			var codeWell = $(e.target).parent().parent().next().next(); 
			restc.ui.jsonwell.removeClass('hidden');
			restc.ui.jsonwell.html(getCodeHtml(data));
		});
	});

	restc.ui.inputTryit.keypress(function(e) {
		if(e.keyCode == 13) {
			$(e.target).next().get(0).firstChild.click();
		}
	});
	
	var navbarLinkActive = function(target) {
		restc.ui.navbarlink.parent().removeClass('active');
		$(target).parent().addClass('active');
	};
	
	var ajax = function(url, callback) {
		$.ajax({
			url: url,
			type: 'GET',
			dataType: 'json',
			success: function(data, textStatus, jqXHR) {
				restc.ui.warning.addClass('hidden');
				callback(data);
			},
			 statusCode: {
				404: function() {
					restc.ui.warning.removeClass('hidden');
					restc.ui.errorMsg.addClass('hidden');
					restc.ui.jsonwell.addClass('hidden');
				},
				500: function() {
					restc.ui.warning.addClass('hidden');
					restc.ui.errorMsg.removeClass('hidden');
					restc.ui.jsonwell.addClass('hidden');
				}
			}
		});
	};
	
	var getCodeHtml = function(data) {
		var html = [];
		html.push('<pre style="border: 0px !important;"><code class="json">');
		html.push(JSON.stringify(data, null, "\t"));
		html.push('</code></pre>');
		return html.join('');
	};
	
});