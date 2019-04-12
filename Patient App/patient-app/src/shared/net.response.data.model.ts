export class NetResponseDataModel {
  public resp_event: number;
  public seq_id: number;
  public action: string;
  public respJson: string;

  constructor(resp_event: number, seq_id1: number, action: string, respJson: string) {
    this.resp_event = resp_event;
    this.seq_id = seq_id1;
    this.action = action;
    this.respJson = respJson;
  }
}
