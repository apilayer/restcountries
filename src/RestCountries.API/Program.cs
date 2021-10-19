//-----------------------------------------------------------------------
// <copyright file="D:\PROJEKTE\restcountries\src\RestCountries.API\Program.cs" company="AXA Partners">
// Author: Jörg H Primke
// Copyright (c) 2021 - AXA Partners. All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

using Microsoft.AspNetCore.Mvc;
using RestCountries.API.Models;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

// builder.Services.AddControllers();

builder.Services.AddAuthorization();

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(c => { c.SwaggerDoc("v1.0", new() { Title = "RestCountries.API", Version = "v1.0" }); });

builder.Services
       .AddSingleton(sp =>
                         new CountryRepository(Path.Combine(builder.Configuration.GetValue<string>("ResourceDirectory"),
                                                            "allcountries.json")));

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1.0/swagger.json", "RestCountries.API v1.0"));
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapGet("countries/all", (CountryRepository repository) => repository.Countries);

app.MapGet("countries/alpha/{alphaCode:alpha:length(2,3)}",
           (CountryRepository repository, string alphaCode) =>
               repository.Countries
                         .Where(c => (c.Alpha2Code.ToLower() == alphaCode.ToLower()) || (c.Alpha3Code.ToLower() == alphaCode.ToLower())));

app.MapGet("countries/alpha",
           (CountryRepository repository, [FromQuery] string codes) =>
           {
               var splitCodes = codes.Split(';', StringSplitOptions.RemoveEmptyEntries | StringSplitOptions.TrimEntries);
               splitCodes = splitCodes.Select(s => s.ToLower()).ToArray();
               return repository.Countries
                                .Where(c => splitCodes.Contains(c.Alpha2Code.ToLower()) || splitCodes.Contains(c.Alpha3Code.ToLower()));
           });

app.MapGet("countries/name/{name}",
           (CountryRepository repository, string name, [FromQuery] bool? fullText) =>
           {
               name = name.ToLower();

               if (fullText ?? false)
               {
                   return repository.Countries.Where(c => (c.Name.ToLower() == name) || (c.NativeName.ToLower() == name));
               }
               else
               {
                   return repository.Countries.Where(c => c.Name.ToLower().Contains(name) || c.NativeName.ToLower().Contains(name));
               }
           });

app.MapGet("countries/region/{region:alpha}",
           (CountryRepository repository, string region) => repository.Countries.Where(c => c.Region.ToLower() == region.ToLower()));

app.MapGet("countries/currency/{currency:alpha}",
           (CountryRepository repository, string currency) =>
               repository.Countries
                         .Where(c =>
                                    c.Currencies
                                     .Any(cur => (cur.Name.ToLower() == currency.ToLower()) || (cur.Code.ToLower() == currency.ToLower()))));

app.MapGet("countries/lang/{lang:alpha}",
           (CountryRepository repository, string lang) =>
           {
               lang = lang.ToLower();
               return repository.Countries
                                .Where(c =>
                                           c.Languages
                                            .Any(l =>
                                                     (l.Iso639_1.ToLower() == lang)
                                                     || (l.Iso639_2.ToLower() == lang)
                                                     || (l.Name.ToLower() == lang)
                                                     || (l.NativeName.ToLower() == lang)));
           });

app.MapGet("countries/callingcode/{callingcode}",
           (CountryRepository repository, string callingcode) => repository.Countries.Where(c => c.CallingCodes.Any(c => c == callingcode)));

app.MapGet("countries/subregion/{subregion:alpha}",
           (CountryRepository repository, string subregion) => repository.Countries.Where(c => c.SubRegion.ToLower() == subregion.ToLower()));

app.MapGet("countries/capital/{capital:alpha}",
           (CountryRepository repository, string capital) => repository.Countries.Where(c => c.Capital.ToLower() == capital.ToLower()));

app.MapGet("countries/regionalBloc/{bloc:alpha}",
           (CountryRepository repository, string bloc) =>
               repository.Countries
                         .Where(c =>
                                    c.RegionalBlocs
                                     .Any(b => (b.Name.ToLower() == bloc.ToLower()) || (b.Acronym.ToLower() == bloc.ToLower()))));

app.MapGet("countries/topleveldoamin/{topleveldomain:alpha}",
           (CountryRepository repository, string topleveldomain) => repository.Countries.Where(c => c.TopLevelDomain.Any(t => t.ToLower() == topleveldomain.ToLower())));

app.MapGet("countries/cioc/{cioc:alpha}",
           (CountryRepository repository, string cioc) => repository.Countries.Where(c => c.Cioc.ToLower() == cioc.ToLower()));

app.Run();
