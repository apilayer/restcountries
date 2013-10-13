var restc = restc || {};

$(function () {
	'use strict';
	
	restc.ui = {
		navbarlink 	: $('.rc-navbar-link'),
		tryit 		: $('.rc-a-tryit'),
		jsonwell 	: $('#rc-json-well')
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
				callback(data);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert("An error has occurred while requesting " + url);
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