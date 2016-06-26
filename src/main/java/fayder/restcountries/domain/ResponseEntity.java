/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package fayder.restcountries.domain;

public class ResponseEntity {
	
	private final int status;
	private final String message;
	
	public ResponseEntity(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

}
