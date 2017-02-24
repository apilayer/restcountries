/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
var restc = restc || {};

$(function () {
	'use strict';
	$(document).on('flatdoc:ready', function() {

		var stripeHandler = StripeCheckout.configure({
		  key: 'pk_test_ZEAAuFImBxBGFGYqTr2Q4Nkr',
		  image: '../img/restc.png',
		  locale: 'auto',
		  // billingAddress: true,
		  panelLabel: 'Contribute',
		  token: function(token) {
		    // You can access the token ID with `token.id`.
		    // Get the token ID to your server-side code for use.
		    console.log(token);
		    $.ajax({
		    	url: 'https://restcountries.eu/contribute',
		    	type: 'POST',
		    	contentType: "application/json; charset=utf-8",
    			dataType: "json",
    			data: JSON.stringify({"token": token.id, "amount": restc.amount}),
    			success: function(data, textStatus, jqXHR) {

    			},
    			error: function(jqXHR, textStatus, errorThrown) {
    				console.log(errorThrown);
    			}
		    });
		  }
		});

        $('a[href="#five"]').click(function(e) {
        	stripeHandler.open({
		    name: 'REST Countries',
		    description: '',
		    currency: 'eur',
		    amount: 500
		});
            restc.amount = 500;
            e.preventDefault();
        });

        $('a[href="#ten"]').click(function(e) {
            stripeHandler.open({
		    name: 'REST Countries',
		    description: '',
		    currency: 'eur',
		    amount: 1000
		});
            restc.amount = 1000;
            e.preventDefault();
        });

        $('a[href="#twenty"]').click(function(e) {
            stripeHandler.open({
		    name: 'REST Countries',
		    description: '',
		    currency: 'eur',
		    amount: 20000
		});
            restc.amount = 20000;
			e.preventDefault();
        });

        $('a[href="#fifty"]').click(function(e) {
            stripeHandler.open({
		    name: 'REST Countries',
		    description: '',
		    currency: 'eur',
		    amount: 50000
		});
            restc.amount = 50000;
            e.preventDefault();
        });

        $('a[href="#seventy"]').click(function(e) {
            stripeHandler.open({
		    name: 'REST Countries',
		    description: '',
		    currency: 'eur',
		    amount: 70000
		});
		  	restc.amount = 70000;
            e.preventDefault();
        });

        $('a[href="#hundred"]').click(function(e) {
            stripeHandler.open({
		    name: 'REST Countries',
		    description: '',
		    currency: 'eur',
		    amount: 100000
		});
		  	restc.amount = 100000;
            e.preventDefault();
        });

        // Close Checkout on page navigation:
		window.addEventListener('popstate', function() {
		  stripeHandler.close();
		});
    });
});
