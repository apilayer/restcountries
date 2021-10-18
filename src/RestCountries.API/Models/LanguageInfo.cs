//-----------------------------------------------------------------------
// <copyright file="D:\PROJEKTE\Bestand\Mosaic\Countries\Countries.Client\LanguageInfo.cs" company="AXA Partners">
// Author: Jörg H Primke
// Copyright (c) 2020 - AXA Partners. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

namespace RestCountries.API.Models;

public class LanguageInfo
{
    public string Iso639_1 { get; set; } = string.Empty;

    public string Iso639_2 { get; set; } = string.Empty;

    public string Name { get; set; } = string.Empty;

    public string NativeName { get; set; } = string.Empty;
}
