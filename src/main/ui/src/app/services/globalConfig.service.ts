// import { environment } from './../../environments/environment.prod';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable()
export class globalConfigService{


    constructor(){
      this.devHostName= environment.url
      this.prodHostName=environment.url;
    }
    isDev=false;
    devHostName:string= environment.url
    prodHostName:string=environment.url;
    // devHostName="http://192.168.43.49:8080";
    // prodHostName="http://192.168.43.49:8080/account";



}
