"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var platform_browser_1 = require("@angular/platform-browser");
var app_component_1 = require("./app/app.component");
var http_1 = require("@angular/common/http"); // ✅ Importiamo il provider HTTP
(0, platform_browser_1.bootstrapApplication)(app_component_1.AppComponent, {
    providers: [(0, http_1.provideHttpClient)()] // ✅ Registriamo il provider HTTP
})
    .catch(function (err) { return console.error(err); });
