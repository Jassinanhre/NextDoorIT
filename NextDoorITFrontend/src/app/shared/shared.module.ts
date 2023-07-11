import { NgModule } from '@angular/core';

import { AccordionAnchorDirective, AccordionLinkDirective, AccordionDirective } from './accordion';

import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { MaterialModule } from './material-module';
import { AppRoutingModule } from '../app-routing.module';
import { SearchComponent } from './search/search.component';

@NgModule({
  imports: [
    MaterialModule,
    AppRoutingModule
  ],
  declarations: [
    HeaderComponent,
    NavbarComponent,
    SidebarComponent,
    FooterComponent,
    SearchComponent,
    AccordionAnchorDirective,
    AccordionLinkDirective,
    AccordionDirective,
  ],
  exports: [
    HeaderComponent,
    NavbarComponent,
    SidebarComponent,
    FooterComponent,
    SearchComponent,
    AccordionAnchorDirective,
    AccordionLinkDirective,
    AccordionDirective,
  ],
  providers: []
})
export class SharedModule { }