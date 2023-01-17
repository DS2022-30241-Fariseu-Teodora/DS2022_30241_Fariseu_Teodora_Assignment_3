import { ChatMessage } from '@/proto/chat_pb'
import { ChatServiceClient } from '@/proto/chat_grpc_web_pb'
const client = new ChatServiceClient("http://localhost:8420", null, null);
export default class Chat {
    async send (message) {
        const req = new ChatMessage()
        req.msg = message
        req.setFrom("Me");
        req.setTime(new Date().toLocaleString());
        client.sendMsg(req, null, (err, response) => {
            console.log(response);
        });
    }
}