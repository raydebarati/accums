import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/components/button/button';
import {CardModule} from 'primeng/card';
import {InputTextModule} from 'primeng/inputtext';
import {MenubarModule} from 'primeng/menubar';
import { HttpClientModule } from '@angular/common/http';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import {DialogModule} from 'primeng/dialog';
import {TableModule} from 'primeng/table';
import { MineService } from './service/mine.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    ButtonModule,
    CardModule,
    InputTextModule,
    MenubarModule,
    DialogModule,
    TableModule,
    HttpClientModule
  ],
  providers: [MineService],
  bootstrap: [AppComponent]
})
export class AppModule { }
