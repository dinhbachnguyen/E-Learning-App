import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@NgModule({
  declarations: [],
  imports: [RouterLink],
  exports: [
    FormsModule,
    CommonModule,
    RouterLink
  ],
  providers: [],
})
export class AppModule { }
