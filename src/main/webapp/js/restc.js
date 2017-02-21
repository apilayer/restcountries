/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
var restc = restc || {};

$(function () {
	'use strict';
	$(document).on('flatdoc:ready', function() {

		var handler = StripeCheckout.configure({
		  key: 'pk_live_lKuAMSLFxniueaO1AcVD81HB',
		  image: 'https://stripe.com/img/documentation/checkout/marketplace.png',
		  locale: 'auto',
		  token: function(token) {
		    // You can access the token ID with `token.id`.
		    // Get the token ID to your server-side code for use.
		  }
		});

        var donationLink = $('a[href="#donation"]');
        donationLink.click(function(e) {
            handler.open({
		    name: 'REST Countries',
		    description: '',
		    currency: 'eur',
		    amount: 2000
		  });
		  e.preventDefault();
        });

        // Close Checkout on page navigation:
		window.addEventListener('popstate', function() {
		  handler.close();
		});
    });
});
