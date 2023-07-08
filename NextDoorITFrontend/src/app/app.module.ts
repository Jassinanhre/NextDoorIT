import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';

import { NgxUiLoaderConfig, NgxUiLoaderModule, SPINNER } from 'ngx-ui-loader';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MaterialModule } from './components/shared/material-module';
import { HomeComponent } from './components/home/home.component';

import { SharedModule } from './components/shared/shared.module';
import { FullComponent } from './components/layouts/full/full.component';
import { AppHeaderComponent } from './components/layouts/full/header/header.component';
import { AppSidebarComponent } from './components/layouts/full/sidebar/sidebar.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { ServiceComponent } from './components/service/service.component';
import { LoginComponent } from './components/auth/login/login.component';

const ngxUiLoaderConfig: NgxUiLoaderConfig = {
  text: "Loading...",
  textColor: "#FFFFFF",
  textPosition: "center-center",
  bgsColor: "#7b1fa2",
  fgsColor: "#7b1fa2",
  fgsType: SPINNER.squareJellyBox,
  fgsSize: 100,
  hasProgressBar: false

}
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FullComponent,
    AppHeaderComponent,
    AppSidebarComponent,
    SignupComponent,
    LoginComponent,
    ServiceComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    FlexLayoutModule,
    SharedModule,
    HttpClientModule,
    NgxUiLoaderModule.forRoot(ngxUiLoaderConfig)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
