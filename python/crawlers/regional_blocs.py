# -*- coding: utf-8 -*-

EU = ["AT","BE","BG","CY","CZ","DE","DK","ES","EE","FI","FR","GB","GR","HR","HU","IE","IT","LT","LU","LV","MT","NL","PL","PT","RO","SK","SI","SE"]
EFTA = ["IS", "LI", "NO", "CH"]
CARICOM = ["AG", "BS", "BB", "BZ", "DO", "GD", "GY", "HT", "JM", "MS", "KN", "LC", "VC", "SR", "TT"]
PA = ["CL", "CO", "MX", "PE"]
AU = [] # Africa region
USAN = [] # South America region
EEU = ["AM", "BY", "KZ", "KG", "RU"]
AL = ["DZ", "BH", "KM", "DJ", "EG", "IQ", "JO", "KW", "LB", "LY", "MR", "MA", "OM", "PS", "QA", "SA", "SO", "SD", "SY", "TN", "AE", "YE"]
ASEAN = ["BN", "KH", "ID", "LA", "MY", "MM", "PH", "SG", "TH", "VN"]
CAIS = ["BZ", "CR", "DO", "SV", "GT", "HN", "NI", "PA"]
CEFTA = ["AL", "BA", "MK", "MD", "ME", "RS", "XK"]
NAFTA = ["CA", "MX", "US"]
SAARC = ["AF", "BD", "BT", "IN", "MV", "NP", "PK", "LK"]
BLOCS = [
	{"acronym" : "EU", "name": "European Union"},
	{"acronym": "EFTA", "name": "European Free Trade Association"},
	{"acronym": "CARICOM", "name": "Caribbean Community", "otherNames": ["Comunidad del Caribe", "Communauté Caribéenne", "Caribische Gemeenschap"]},
	{"acronym": "PA", "name": "Pacific Alliance", "otherNames": ["Alianza del Pacífico"]},
	{"acronym": "AU", "name": "African Union", "otherNames": ["الاتحاد الأفريقي", "Union africaine", "União Africana", "Unión Africana", "Umoja wa Afrika"]},
	{"acronym": "USAN", "name": "Union of South American Nations", "otherAcronyms": ["UNASUR", "UNASUL", "UZAN"], "otherNames": ["Unión de Naciones Suramericanas", "União de Nações Sul-Americanas", "Unie van Zuid-Amerikaanse Naties", "South American Union"]},
	{"acronym": "EEU", "name": "Eurasian Economic Union", "otherAcronyms": ["EAEU"]},
	{"acronym": "AL", "name": "Arab League", "otherNames" : ["جامعة الدول العربية", "Jāmiʻat ad-Duwal al-ʻArabīyah", "League of Arab States"]},
	{"acronym": "ASEAN", "name": "Association of Southeast Asian Nations"},
	{"acronym": "CAIS", "name": "Central American Integration System", "otherAcronyms": ["SICA"], "otherNames": ["Sistema de la Integración Centroamericana,"]},
	{"acronym": "CEFTA", "name": "Central European Free Trade Agreement"},
	{"acronym": "NAFTA", "name": "North American Free Trade Agreement", "otherNames": ["Tratado de Libre Comercio de América del Norte", "Accord de Libre-échange Nord-Américain"]},
	{"acronym": "SAARC", "name": "South Asian Association for Regional Cooperation"}
]

def update(countriesJSON):
	for countryJson in countriesJSON:
		countryJson['regionalBlocs'] = []
		code = countryJson['alpha2Code']
		region = countryJson['region']
		subregion = countryJson['subregion']
		if code in EU:
			countryJson['regionalBlocs'].append(BLOCS[0])
		if code in EFTA:
			countryJson['regionalBlocs'].append(BLOCS[1])
		if code in CARICOM:
			countryJson['regionalBlocs'].append(BLOCS[2])
		if code in PA:
			countryJson['regionalBlocs'].append(BLOCS[3])
		if region == 'Africa':
			countryJson['regionalBlocs'].append(BLOCS[4])
		if subregion == 'South America':
			countryJson['regionalBlocs'].append(BLOCS[5])
		if code in EEU:
			countryJson['regionalBlocs'].append(BLOCS[6])
		if code in AL:
			countryJson['regionalBlocs'].append(BLOCS[7])
		if code in ASEAN:
			countryJson['regionalBlocs'].append(BLOCS[8])
		if code in CAIS:
			countryJson['regionalBlocs'].append(BLOCS[9])
		if code in CEFTA:
			countryJson['regionalBlocs'].append(BLOCS[10])
		if code in NAFTA:
			countryJson['regionalBlocs'].append(BLOCS[11])
		if code in SAARC:
			countryJson['regionalBlocs'].append(BLOCS[12])
	return countriesJSON
