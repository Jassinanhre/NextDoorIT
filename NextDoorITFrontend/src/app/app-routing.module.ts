import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { ServiceComponent } from './modules/service/service.component';
import { ServiceDetailsComponent } from './modules/service/service-details/service-details.component';
import { SignupComponent } from './modules/auth/signup/signup.component';
import { LoginComponent } from './modules/auth/login/login.component';
import { MainLayoutComponent } from './modules/layouts/main/main.component';
import { DefaultLayoutComponent } from './modules/layouts/default/default.component';
import { AboutUsComponent } from './modules/common/about-us/about-us.component';
import { CareerComponent } from './modules/common/career/career.component';
import { ContactUsComponent } from './modules/common/contact-us/contact-us.component';
import { ServiceListComponent } from './modules/service/service-list/service-list.component';
import { ProductComponent } from './modules/products/product.component';
import { ProductListComponent } from './modules/products/product-list/product-list.component';
import { ProductDetailsComponent } from './modules/products/product-details/product-details.component';
import { TrainingComponent } from './modules/training/training.component';
import { TrainingListComponent } from './modules/training/training-list/training-list.component';
import { TrainingDetailsComponent } from './modules/training/training-details/training-details.component';
import { CartComponent } from './modules/cart/cart.component';
import { CheckoutComponent } from './modules/checkout/checkout.component';
import { ShippingComponent } from './modules/shipping/shipping.component';
import { OrderComponent } from './modules/order/order.component';
import { PaymentComponent } from './modules/payment/payment.component';

const routes: Routes = [
  // otherwise redirect to Home Component
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: '', component: MainLayoutComponent,
    children: [
      {
        path: 'service', component: ServiceComponent,
        children: [
          { path: 'list', component: ServiceListComponent },
          { path: ':id', component: ServiceDetailsComponent }
        ]
      },
      {
        path: 'product', component: ProductComponent,
        children: [
          { path: 'list', component: ProductListComponent },
          { path: ':id', component: ProductDetailsComponent }
        ]
      },
      {
        path: 'training', component: TrainingComponent,
        children: [
          { path: 'list', component: TrainingListComponent },
          { path: ':id', component: TrainingDetailsComponent }
        ]
      },
      {
        path: 'cart', component: CartComponent,
      },
      {
        path: 'checkout', component: CheckoutComponent,
      },
      {
        path: 'shipping', component: ShippingComponent,
      },
      {
        path: 'order', component: OrderComponent,
      },
      {
        path: 'payment', component: PaymentComponent,
      }
    ]
  }, {
    path: '', component: DefaultLayoutComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'aboutUs', component: AboutUsComponent },
      { path: 'contactUs', component: ContactUsComponent },
      { path: 'career', component: CareerComponent },
      { path: 'login', component: LoginComponent },
      { path: 'signup', component: SignupComponent }
    ]
  },

  // otherwise redirect to Error Component
  { path: '**', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
