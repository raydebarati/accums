
export class Block {
    blockNumber: Number;
    hash:String;
    previousHash:String;
    timeStamp:Number
    nonce: Number;
    memberLedgerList:Array<any>;
    validChain: boolean;
    class: String;
}
