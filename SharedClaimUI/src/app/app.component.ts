import { Component } from '@angular/core';
import { ViewEncapsulation } from '@angular/core';
import { Block } from './model/block';
import { Data } from './data';
import {MineService } from './service/mine.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class AppComponent {
    title = 'SharedClaimUI';
    blocks: Block[] = [];
    display = false;
    currentBlock = {};
    cols: any[];
    globalHash: string;
    updatedMemerDataList= [];
    errorMessage: string;

    ngOnInit() {

        this.cols = [
            { field: 'memberId', header: 'Member Id' },
            { field: 'utilization', header: 'Current  Utilization' },
            { field: 'remaining', header: 'Remaining amount' },
            { field: 'limit', header: 'Limit' }
        ];


        let data = [];
        let d = new Data();
        d.memberId = 1;
        d.utilization = 100;
        d.limit = 500;
        d.remaining = 400;

        let d1 = new Data();
        d1.memberId = 2;
        d1.utilization = 200;
        d1.limit = 500;
        d1.remaining = 300;


        let d2 = new Data();
        d2.memberId = 3;
        d2.utilization = 1500;
        d2.limit = 5000;
        d2.remaining = 3500;


        let d3 = new Data();
        d3.memberId = 4;
        d3.utilization = 2200;
        d3.limit = 6600;
        d3.remaining = 4400;

        let d4 = new Data();
        d4.memberId = 5;
        d4.utilization = 100;
        d4.limit = 500;
        d4.remaining = 400;

        data.push(d);
        data.push(d1);
        data.push(d2);
        data.push(d3);
        data.push(d4);


        let block = new Block();
        block.blockNumber = 1;
        block.nonce = 123;
        block.hash = '123sffa';
        block.memberLedgerList = data;
        block.class = "ui-card-shadow";
        block.validChain = false;

        let block1 = new Block();
        block1.blockNumber = 2;
        block1.nonce = 123;
        block1.hash = '123sffa';
        block1.memberLedgerList = data;
        block1.class = "ui-card-shadow";
        block1.validChain = false;


        let block2 = new Block();
        block2.blockNumber = 3;
        block2.nonce = 123;
        block2.hash = '123sffa';
        block2.memberLedgerList = data;
        block2.class = "ui-card-shadow";
        block2.validChain = false;

        this.blocks.push(block);
        this.blocks.push(block1);
        this.blocks.push(block2);
    }


    constructor(private mineservice: MineService) {

    }

    showMemberData(block : Block){
        this.currentBlock = block;
        this.display = true;
    }

    onChangeMemberData(data : any){
        this.updatedMemerDataList.push(data);
    }

    updateMemberDetails(){
        this.display = false;
       // this.currentBlock.hash = '23$qdafs90';
        this.globalHash = '23$qdafs90';// this.currentBlock.hash;

        this.blocks.forEach(block => {
            if(block.hash != this.globalHash){
                block.class =  "ui-card-shadow class1";
                block.validChain = true; 
            }
        })
    }

    // mine(block : Block){
    //     block.hash = this.globalHash;
    //     block.class = "ui-card-shadow";
    //     block.isDirty = false;

        
    // }

    // mine(){
    //     this.blocks.forEach(block=>{
    //         block.hash = this.globalHash;
    //     block.class = "ui-card-shadow";
    //     block.isDirty = false; 
    //     })
    // }

    mine(){
        debugger;
    this.mineservice.mine().subscribe(
        products => {
          this.blocks = products;
          //this.filteredProducts = this.products;
        },
        error => this.errorMessage = <any>error
      );
    }
}
