//-----------------------------------------------------------------------
// <copyright file="D:\PROJEKTE\restcountries\src\RestCountries.API\Models\CountryRepository.cs" company="AXA Partners">
// Author: Jörg H Primke
// Copyright (c) 2021 - AXA Partners. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

using System.Text.Json;

namespace RestCountries.API.Models;

public class CountryRepository
{
    public IEnumerable<CountryInfo> Countries { get; }

    public CountryRepository(string fileName)
    {
        Countries = JsonSerializer.Deserialize<List<CountryInfo>>(File.OpenRead(fileName), new JsonSerializerOptions(JsonSerializerDefaults.Web)) ?? new();
    }
}
