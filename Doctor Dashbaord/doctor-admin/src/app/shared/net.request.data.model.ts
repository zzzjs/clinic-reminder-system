export class NetRequestDataModel {
  public action: string;
  public reqEvent: number;
  public seqId: number;
  public reqJson: any;

  constructor(action: string, reqEvent: number, seqId: number, reqJson: any) {
    this.action = action;
    this.reqEvent = reqEvent;
    this.seqId = seqId;
    this.reqJson = reqJson;
  }
}
