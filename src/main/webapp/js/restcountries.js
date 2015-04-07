/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
var restc = restc || {};

$(function () {
	'use strict';
	
	restc.ui = {
		navbarlink 	: $('.rc-navbar-link'),
		tryit 		: $('.rc-a-tryit'),
		inputTryit 	: $('.rc-input-tryit'),
		jsonwell 	: $('#rc-json-well'),
		warning 	: $('#rc-warning'),
		errorMsg	: $('#rc-error'),
		searchers 	: $('#rc-searchers'),
		tryityou	: $('.rc-a-diy')	
	};
	
	restc.ui.navbarlink.click(function(e) {
		navbarLinkActive(e.target);
	});

	restc.ui.tryityou.click(function(e) {
		ajax($(e.target).parent().prev().val(), function(data) {
			restc.ui.jsonwell.removeClass('hidden');
			restc.ui.jsonwell.html(getCodeHtml(data));
			$(document).scrollTop(restc.ui.searchers.get(0).clientHeight);
		});
	});
	
	restc.ui.tryit.click(function(e) {
		ajax($(e.target).html(), function(data) {
			restc.ui.jsonwell.removeClass('hidden');
			restc.ui.jsonwell.html(getCodeHtml(data));
			restc.ui.inputTryit.val($(e.target).html());
			$(document).scrollTop(restc.ui.searchers.get(0).clientHeight);
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
		if(
			url.indexOf('restcountries.eu') != 7 &&
			url.indexOf('restcountries.eu') != 8 &&
			url.indexOf('restcountries.eu') != 11 &&
			url.indexOf('restcountries.eu') != 12 &&
			url.indexOf('localhost') != 7)
			return;
		
		$.ajax({
			url: url,
			type: 'GET',
			dataType: 'json',
			success: function(data, textStatus, jqXHR) {
				restc.ui.warning.addClass('hidden');
				restc.ui.errorMsg.addClass('hidden');
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
		html.push('<pre style="border: 0px !important; font-size: 9pt;"><code class="json">');
		html.push(JSON.stringify(data, null, "\t"));
		html.push('</code></pre>');
		return html.join('');
	};
	
});