//-----------------------------------------------------------------------
// <copyright file="D:\PROJEKTE\Bestand\Mosaic\Countries\Countries.Client\CountryInfo.cs" company="AXA Partners">
// Author: Jörg H Primke
// Copyright (c) 2020 - AXA Partners. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

using System.Text.Json.Serialization;

namespace RestCountries.API.Models;

public class CountryInfo
{
    public string Name { get; set; } = string.Empty;

    [JsonPropertyName("topLevelDomain")]
    public List<string> TopLevelDomain { get; set; } = new List<string>();

    public string Alpha2Code { get; set; } = string.Empty;

    public string Alpha3Code { get; set; } = string.Empty;

    public List<string> CallingCodes { get; set; } = new List<string>();

    public string Capital { get; set; } = string.Empty;

    public List<string> AltSpellings { get; set; } = new List<string>();

    public string SubRegion { get; set; } = string.Empty;

    public string Region { get; set; } = string.Empty;

    public int Population { get; set; }

    [JsonPropertyName("latlng")]
    public List<double> LatLng { get; set; } = new List<double>();

    public string Demonym { get; set; } = string.Empty;

    public double? Area { get; set; }

    [JsonPropertyName("timezones")]
    public List<string> TimeZones { get; set; } = new List<string>();

    public List<string> Borders { get; set; } = new List<string>();

    public string NativeName { get; set; } = string.Empty;

    public string NumericCode { get; set; } = string.Empty;

    public List<CurrencyInfo> Currencies { get; set; } = new List<CurrencyInfo>();

    public List<LanguageInfo> Languages { get; set; } = new List<LanguageInfo>();

    public Dictionary<string, string> Translations { get; set; } = new Dictionary<string, string>();

    public Dictionary<string, string> Flags { get; set; } = new();

    public string Flag { get; set; } = string.Empty;

    public string Cioc { get; set; } = string.Empty;

    public List<BlocInfo> RegionalBlocs { get; set; } = new List<BlocInfo>();

    public bool Independent { get; set; }
}
