import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { ServiceComponent } from './modules/service/service.component';
import { ServiceDetailsComponent } from './modules/service/service-details/service-details.component';
import { SignupComponent } from './modules/auth/signup/signup.component';
import { LoginComponent } from './modules/auth/login/login.component';
import { MainLayoutComponent } from './layouts/main/main.component';
import { DefaultLayoutComponent } from './layouts/default/default.component';
import { AboutUsComponent } from './modules/common/about-us/about-us.component';
import { CareerComponent } from './modules/common/career/career.component';
import { ContactUsComponent } from './modules/common/contact-us/contact-us.component';

const routes: Routes = [
  {
    path: '', component: MainLayoutComponent,
    children: [
      { path: '', component: HomeComponent },
      {
        path: 'service', component: ServiceComponent,
        children: [
          { path: '', component: ServiceComponent },
          { path: 'service/:id', component: ServiceDetailsComponent }
        ]
      }]
  }, {
    path: '', component: DefaultLayoutComponent,
    children: [
      { path: 'aboutUs', component: AboutUsComponent },
      { path: 'contactUs', component: ContactUsComponent },
      { path: 'career', component: CareerComponent },
      { path: 'login', component: LoginComponent },
      { path: 'signup', component: SignupComponent }
    ]
  },

  // otherwise redirect to home
  { path: '**', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
