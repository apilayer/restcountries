/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
var restc = restc || {};

$(function () {
	'use strict';
	$(document).on('flatdoc:ready', function() {

		if (location.hostname != "localhost" && location.hostname != "127.0.0.1" && location.protocol != 'https:') {
			location.href = location.href.replace("http://", "https://");
		}

		var stripeHandler = StripeCheckout.configure({
		  key: 'pk_live_lKuAMSLFxniueaO1AcVD81HB',
		  image: 'https://restcountries.eu/img/rc-icon.png',
		  locale: 'auto',
		  billingAddress: true,
		  panelLabel: 'Contribute',
		  token: function(token) {
		    // You can access the token ID with `token.id`.
		    // Get the token ID to your server-side code for use.
		    $.ajax({
		    	url: 'https://restcountries.eu/rest/contribute',
		    	type: 'POST',
		    	contentType: "application/json; charset=utf-8",
    			dataType: "json",
    			data: JSON.stringify({"token": token.id, "amount": restc.amount}),
    			success: function(data, textStatus, jqXHR) {
    				console.log("Contribution success");
    			},
    			error: function(jqXHR, textStatus, errorThrown) {
    				console.log(textStatus);
    				alert("Your contribution failed. Please contact support@restcountries.eu");
    			}
		    });
		  }
		});

        $('a[href="#five"]').click(function(e) {
        	stripeHandler.open({
		    name: 'REST Countries',
		    description: 'Secured by Stripe',
		    currency: 'eur',
		    amount: 500
		});
            restc.amount = 500;
            e.preventDefault();
        });

        $('a[href="#ten"]').click(function(e) {
            stripeHandler.open({
		    name: 'REST Countries',
		    description: 'Secured by Stripe',
		    currency: 'eur',
		    amount: 1000
		});
            restc.amount = 1000;
            e.preventDefault();
        });

        $('a[href="#twenty"]').click(function(e) {
            stripeHandler.open({
		    name: 'REST Countries',
		    description: 'Secured by Stripe',
		    currency: 'eur',
		    amount: 2000
		});
            restc.amount = 2000;
			e.preventDefault();
        });

        $('a[href="#fifty"]').click(function(e) {
            stripeHandler.open({
		    name: 'REST Countries',
		    description: 'Secured by Stripe',
		    currency: 'eur',
		    amount: 5000
		});
            restc.amount = 5000;
            e.preventDefault();
        });

        $('a[href="#seventy"]').click(function(e) {
            stripeHandler.open({
		    name: 'REST Countries',
		    description: 'Secured by Stripe',
		    currency: 'eur',
		    amount: 7000
		});
		  	restc.amount = 7000;
            e.preventDefault();
        });

        $('a[href="#hundred"]').click(function(e) {
            stripeHandler.open({
		    name: 'REST Countries',
		    description: 'Secured by Stripe',
		    currency: 'eur',
		    amount: 10000
		});
		  	restc.amount = 10000;
            e.preventDefault();
        });

        // Close Checkout on page navigation:
		window.addEventListener('popstate', function() {
		  stripeHandler.close();
		});
    });
});
