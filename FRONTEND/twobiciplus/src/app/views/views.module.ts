import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AgmCoreModule } from '@agm/core';

import { CalendarModule,  } from 'angular-calendar';
import { SharedModule } from '../shared/shared.module';

import { FooterComponent } from '../main-layout/footer/footer.component';
import { BasicTableComponent } from './tables/basic-table/basic-table.component';
import { ModalsComponent } from './modals/modals.component';
import { Map1Component } from './maps/map1/map1.component';
import { StatsCardComponent } from './dashboards/common/stats-card/stats-card.component';
import { StatsCard2Component } from './dashboards/common/stats-card2/stats-card2.component';
import { Dashboard1Component } from './dashboards/dashboard1/dashboard1.component';
import { Profile1Component } from './profile/profile1/profile1.component';
import { HelpComponent } from './help/help.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthService } from '../services/auth.service';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NavigationComponent } from '../main-layout/navigation/navigation.component';
import { CarritoComponent } from './carrito/carrito.component';
import { PerfilComponent } from './perfil/perfil.component';
import { ChatBotComponent } from './chat-bot/chat-bot.component';
import { ChatSellerComponent } from './chat-seller/chat-seller.component';
import { ComplexProductsComponent } from './complex-products/complex-products.component';
import { ChatCustomerComponent } from './chat-customer/chat-customer.component';
import { ProductsCRUDComponent } from './products-crud/products-crud.component';


@NgModule({
  providers: [
    AuthService
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    SharedModule,
    AgmCoreModule.forRoot({
      // https://developers.google.com/maps/documentation/javascript/get-api-key?hl=en#key
      apiKey: ''
    }),
    CalendarModule.forRoot()
  ],
  declarations: [
    FooterComponent,
    BasicTableComponent,
    ModalsComponent,
    Map1Component,
    StatsCardComponent,
    StatsCard2Component,
    Dashboard1Component,
    Profile1Component,
    HelpComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    NavigationComponent,
    CarritoComponent,
    PerfilComponent,
    ChatBotComponent,
    ChatSellerComponent,
    ComplexProductsComponent,
    ChatCustomerComponent,
    ProductsCRUDComponent
  ],
  exports: [
    FooterComponent,
    BasicTableComponent,
    ModalsComponent,
    Map1Component,
    StatsCardComponent,
    StatsCard2Component,
    Dashboard1Component,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    CarritoComponent,
    PerfilComponent,
    ComplexProductsComponent
  ],
  schemas: [NO_ERRORS_SCHEMA]
})
export class ViewsModule { }
