<template>
  <div>
    <div class="chat">
      <el-container class="container">
        <el-aside class="user-list" style="overflow-x: hidden">
          <!-- <el-row class="list" style="cursor: pointer">
            <el-col :span="5" style="margin-top: 16px">
              <img src="./../../assets/imgs/clean.png" alt="" />
            </el-col>
            <el-col :span="19" style="position: absolute; top: 40%; left: 25%">
              <div>全部已读</div>
            </el-col>
          </el-row> -->
          <!-- <el-row class="list" style="cursor: pointer">
            <el-col :span="5" style="margin-top: 16px">
              <img src="./../../assets/imgs/clean.png" alt="" />
            </el-col>
            <el-col :span="19" style="position: absolute; top: 40%; left: 25%">
              <div>全部已读</div>
            </el-col>
          </el-row> -->
          <el-divider style="margin-top: 58px"></el-divider>
          <div>
            <el-row class="list" style="background-color: aliceblue">
              <el-col :span="5">
                <img v-if="currentUser.uavatar" :src="'data:image/png;base64,' + currentUser.uavatar" />
                <img v-else
                  src="https://img1.baidu.com/it/u=2893147358,3393430132&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
                  alt="" />
              </el-col>
              <el-col :span="19">
                <div style="font-size: 16px">{{ currentUser.uname }}</div>
                <div style="font-size: 12px; bottom: 5px; position: absolute">
                  <span style="color: #405253">当前对话</span>
                </div>
              </el-col>
              <div class="chat-now"></div>
            </el-row>
            <!-- <el-divider></el-divider> -->
          </div>
          <div>
            <el-row class="list list1" style="font-size: 14px">联系列表<span class="el-icon-chat-line-round"
                style="font-size: 19px; margin-left: 5px; color: #adacac"></span></el-row>
          </div>
          <!-- <el-divider></el-divider> -->
          <div v-for="(item, index) in chatList" @click="standChange(item, index)">
            <el-row class="list" style="cursor: pointer">
              <el-col :span="5">
                <img v-if="item.uavatar" :src="'data:image/png;base64,' + item.uavatar" />
                <img v-else
                  src="https://img1.baidu.com/it/u=2893147358,3393430132&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
                  alt="" />
              </el-col>
              <el-col :span="19">
                <div style="font-size: 16px">{{ item.uname }}</div>
                <div style="font-size: 12px; bottom: 5px; position: absolute">
                  <span v-if="currentUser.uid == item.uid" style="color: #405253">当前对话</span>
                  <span v-else>进行对话</span>
                </div>
              </el-col>
              <div class="chat-now" v-if="currentUser.uid == item.uid"></div>
              <!-- <div class="point" v-show="item.new != undefined && item.new != 0">
                <div class="num">
                  {{ item.new }}
                </div>
              </div> -->
            </el-row>
            <el-divider></el-divider>
          </div>
        </el-aside>
        <el-container>
          <el-header class="chatObj"><span v-show="currentUser.uname != undefined">与{{ currentUser.uname
              }}的对话</span></el-header>
          <!-- 怎么一左一右，然后自动换到下一行，长度自动扩大 -->
          <el-main class="detail">
            <div class="mark">
              <div class="message" v-for="(item, index) in msgList" :key="index">
                <div v-show="item.chattime != undefined &&
                item.chattime != '' &&
                index != 0 &&
                formatTime(item.chattime, index - 1)
                " class="date">
                  {{ item.chattime.substr(0, 10) }}
                </div>
                <span :class="item.uid1 == userInfo.uid ? 'receiver' : 'sender'">
                  <!-- <img v-if="item.uavatar" :class="item.uid1 == userInfo.uid ? 'it' : 'me'"
                                        :src="'data:image/png;base64,' + userInfo.uavatar">
                                    <img v-else :class="item.uid1 == userInfo.uid ? 'it' : 'me'"
                                        src="https://img1.baidu.com/it/u=2893147358,3393430132&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
                                        alt="">
                                        <img v-if="item.uavatar" :class="item.uid1 == userInfo.uid ? 'it' : 'me'"
                                        :src="'data:image/png;base64,' + userInfo.uavatar">
                                    <img v-else :class="item.uid1 == userInfo.uid ? 'it' : 'me'"
                                        src="https://img1.baidu.com/it/u=2893147358,3393430132&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
                                        alt=""> -->
                  <div v-if="item.uid1 == userInfo.uid">
                    <img v-if="userInfo.uavatar" class="me" :src="'data:image/png;base64,' + userInfo.uavatar" />
                    <img v-else class="me"
                      src="https://img1.baidu.com/it/u=2893147358,3393430132&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
                      alt="" />
                  </div>
                  <div v-else>
                    <img v-if="currentUser.uavatar" class="it" :src="'data:image/png;base64,' + currentUser.uavatar" />
                    <img v-else class="it"
                      src="https://img1.baidu.com/it/u=2893147358,3393430132&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
                      alt="" />
                  </div>
                </span>
                <p :class="item.uid1 == userInfo.uid ? 'content content2' : 'content content1'
                ">
                  {{ item.chatcontent }}
                </p>
              </div>
              <!-- <div class="message" >
                                <span class="receiver"><img class="it" src="./../../assets/logo.png" alt=""></span>
                                <p class="content content2">
                                    自己消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2
                                </p>
                            </div> -->
              <!-- <div class="message">
                                <span class="sender"><img class="it" src="./../../assets/logo.png" alt=""></span>
                                <p class="content content1">消息内容1对方</p>
                            </div>

                            <div class="message">
                                <span class="receiver"><img class="it" src="./../../assets/logo.png" alt=""></span>
                                <p class="content content2">消息内容2</p>
                            </div>
                            <div class="message">
                                <span class="sender"><img class="it" src="./../../assets/logo.png" alt=""></span>
                                <p class="content content1">消息内容1</p>
                            </div>
                            <div class="message">
                                <span class="sender"><img class="it" src="./../../assets/logo.png" alt=""></span>
                                <p class="content content1">消息内容1</p>
                            </div>
                            <div class="message">
                                <span class="receiver"><img class="it" src="./../../assets/logo.png" alt=""></span>
                                <p class="content content2">
                                    自己消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2消息内容2
                                </p>
                            </div>
                            <div class="message">
                                <span class="receiver"><img class="it" src="./../../assets/logo.png" alt=""></span>
                                <p class="content content2">消息内容2</p>
                            </div> -->
            </div>
            <!-- 显示的表情 -->
            <div class="chart-form">
              <div class="emoji" v-show="isEmoji">
                <li class="emojis" v-for="(item, index) in emojis">
                  <button>{{ item }}</button>
                </li>
              </div>

              <div class="fun">
                <!-- <label for="file"><img src="./../../assets/imgs/file.png" alt=""></label>
                                <input type="file" id="file" style="display: none;">
                                <div class="btn1" id="btn1" @click="emoji"
                                    style="height: 20px;width: 20px;cursor:pointer;">
                                    😀</div> -->
              </div>
              <div class="chart-from-content">
                <div contenteditable="true" class="chart-from-value" ref="input"></div>
                <div class="chart-from-btn">
                  <el-button type="success" style="background-color: #b7eaed; border-color: #b7eaed" size="small"
                    @click="websocketSend">发送</el-button>
                </div>
              </div>
            </div>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </div>
</template>

<script>
import { _addChat, _listChatUser, _listNew } from "@/api/chat";
import { _serachUid } from "@/api/user";
export default {
  data() {
    return {
      emojis: [
        "😀",
        "😄",
        "😅",
        "🤣",
        "😂",
        "😉",
        "😊",
        "😍",
        "😘",
        "😜",
        "😝",
        "😏",
        "😒",
        "🙄",
        "😔",
        "😴",
        "😷",
        "🤮",
        "🥵",
        "😎",
        "😮",
        "😰",
        "😭",
        "😱",
        "😩",
        "😡",
        "💀",
        "👽",
        "🤓",
        "🥳",
        "😺",
        "😹",
        "😻",
        "🤚",
        "💩",
        "👍",
        "👎",
        "👏",
        "🙏",
        "💪",
      ],
      isEmoji: false,
      chatList: [],
      // msgList: [],
      // chatList: [{
      //     uid2: '友a'
      // }, {
      //     uid2: '友b'
      // },
      // {
      //     uid2: '友c'
      // }, {
      //     uid2: '友d'
      // }],
      msgList: [],
      input: "",
      userInfo: {},
      currentUser: {},
      usersList: [],
    };
  },
  methods: {
    formatTime(timeStr, index) {
      // 将字符串转换为日期对象
      if (timeStr != undefined && timeStr != "") {
        const date = new Date(timeStr);
        const date1 = new Date(this.msgList[index].chattime);
        const diff = date - date1;
        const diffMs = Math.abs(diff);
        const diffDays = Math.ceil(diffMs / (1000 * 60 * 60 * 24));
        console.log(diffDays);
        if (diffDays > 1) {
          return true;
        } else {
          return false;
        }
      }
    },
    async listNew() {
      try {
        let res = await _listNew({ uid1: this.userInfo.uid });
        if (res.status == 200) {
          res = res.data;
          for (let i = 0; i < res.length; ++i) {
            if (res[i].new == undefined) res[i].new = 1;
            else res[i].new += 1;
            this.chatList.push(res[i]);
            this.usersList.push(res[i].uid);
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    async listChatUser() {
      try {
        if (this.userInfo.uid != undefined && this.currentUser.uid != undefined) {
          let res = await _listChatUser({
            uid1: this.userInfo.uid,
            uid2: this.currentUser.uid,
          });
          if (res.status == 200) {
            if (res.data) {
              this.msgList = res.data;
              console.log("msg", res.data);
            }
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    emoji() {
      this.isEmoji = !this.isEmoji;
    },
    standChange(e, index) {
      console.log(e);
      this.currentUser = e;
      let temp = e;
      e.new = 0;
      this.chatList.splice(index, 1, e);
      // 得到uid2
      this.listChatUser();
    },
    initWebSocket() {
      var url = "ws://localhost:80/chatRoom/" + this.userInfo.uid;
      this.websock = new WebSocket(url);
      this.websock.onopen = this.websocketOnopen;
      this.websock.onerror = this.websocketOnerror;
      this.websock.onmessage = this.websocketOnmessage;
      this.websock.onclose = this.websocketOnclose;
    },
    websocketOnopen() {
      console.log("WebSocket连接成功");
    },
    websocketOnerror(e) {
      console.log("WebSocket连接发生错误");
      this.reconnect();
    },
    websocketOnclose(e) {
      console.log("connection closed (" + e + ")");
      if (e) {
        console.log("connection closed (" + e.code + ")");
      }
      this.reconnect();
    },
    websocketSend(text) {
      // 数据发送
      try {
        let inputValue = this.$refs.input.innerText;
        // console.log(this.$refs.input)
        // this.msgList.push({
        //     uid1: 11,
        //     uid2: 3,
        //     chatcontent: inputValue
        // })
        this.$refs.input.innerText = "";
        if (this.currentUser.uid != undefined) {
          // console.log('inputValue', inputValue + "-" + this.currentUser.uid)
          let inputValue1 = inputValue + "|-|" + this.currentUser.uid;
          this.websock.send(inputValue1);
          // 写入数据库
          if (this.userInfo.uid != "") {
            let msg = [this.userInfo.uid, inputValue, this.currentUser.uid];
            this.addChat(msg);
          }
        } else {
          this.$message({
            type: "wrong",
            message: "请选择用户",
          });
        }
      } catch (err) {
        console.log("send failed (" + err + ")");
      }
    },
    websocketOnmessage(e) {
      // 数据接收
      // uid2: oppositeUid,
      console.log(e.data);
      let msg1 = e.data;
      let msg = msg1.split("|-|");
      // console.log(msg[0], this.userInfo.uid, this.currentUser.uid)
      if (
        (msg[0] == this.userInfo.uid && msg[2] == this.currentUser.uid) ||
        (msg[2] == this.userInfo.uid && msg[0] == this.currentUser.uid)
      ) {
        let chattime = new Date();
        chattime = chattime.toString();
        this.msgList.push({
          uid1: msg[0],
          uid2: msg[2],
          chatcontent: msg[1],
          chattime: chattime,
        });
        this.$nextTick(() => {
          // 获取滚动容器的DOM元素
          const scrollContainer = document.querySelector(".mark");
          // 滚动到底部
          scrollContainer.scrollTop = scrollContainer.scrollHeight;
        });
      }
      let set = new Set(this.usersList);
      console.log(this.usersList, msg[0], msg[2])
      console.log(set.has(1))
      // if (!set.has(msg[0]) || !set.has(msg[2])) {
      // 插入
      if (this.userInfo.uid != msg[0]) {
        // 被收消息
        if (!set.has(msg[0])) {
          this.usersList.push(msg[0])
          console.log('msg[0]', msg[0], set, !set.has(msg[0]))
          this.findUser(msg[0]);
        }
      } else {
        if (!set.has(msg[2])) {
          this.usersList.push(msg[2])
          console.log('msg[2]', msg[2], set, !set.has(msg[2]))
          this.findUser(msg[2]);
        }
      }
      // }
      if (this.userInfo.uid != msg[0]) {
        // 进行信息提示（新信息）
      }
    },
    async findUser(data) {
      try {
        data = parseInt(data, 10);
        let res = await _serachUid({
          uid: data,
        });
        if (res.status == 200) {
          if (res.data) {
            // if (res[0].new == undefined) res[0].new = 1;
            // else res[0].new += 1;
            res = res.data
            // if ()
            this.chatList.push(res[0]);
            // this.usersList.push(res[0].uid)
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    async addChat(data) {
      try {
        let res = await _addChat({
          uid1: data[0],
          uid2: data[2],
          chatcontent: data[1],
          chatnew: 1,
        });
      } catch (error) {
        console.log(error);
      }
    },
    reconnect() {
      var that = this;
      if (that.lockReconnect) return;
      that.lockReconnect = true;
      //没连接上会一直重连，设置延迟避免请求过多
      setTimeout(function () {
        console.info("尝试重连...");
        that.initWebSocket();
        that.lockReconnect = false;
      }, 5000);
    },
  },
  mounted() {
    this.userInfo = this.$store.state.users.userInfo;
    this.chatList = [];
    // this.listNew();
    if (this.$route.params != "") {
      // this.chatList.push(this.$route.params)
      this.currentUser = this.$route.params;
      this.$store.commit("setStore0", this.currentUser);
    } else if (this.$store.state.users.Store0 != "") {
      // this.chatList.push(this.$store.state.users.Store0)
      this.currentUser = this.$store.state.users.Store0;
    }
    this.listChatUser();
    this.initWebSocket();
    this.$nextTick(() => {
      // 获取滚动容器的DOM元素
      const scrollContainer = document.querySelector(".mark");
      // 滚动到底部
      scrollContainer.scrollTop = scrollContainer.scrollHeight;
    });
  },
};
</script>

<style lang="scss" scoped>
.chat {
  position: relative;
  width: 80%;
  height: 85vh;
  // background-color: rgba(168, 169, 216, 0.4);
  margin: 0 auto;
  margin-top: 10px;

  .message {
    padding: 10px;
    clear: both;
    overflow: hidden;
  }

  .message {
    margin-bottom: 10px;

    /* 每条消息之间的间隔 */
    .date {
      background-color: rgba(0, 0, 0, 0.1);
      width: 10%;
      border-radius: 10px 10px;
      margin: 0 auto;
    }
  }

  .sender,
  .receiver {
    display: inline-block;
    padding: 5px 8px;
    background-color: #eaeaea;
    border-radius: 4px;
  }

  .sender {
    float: left;
  }

  .receiver {
    float: right;
  }

  .content {
    clear: both;
    word-wrap: break-word;
    /* 自动换行文本 */
    max-width: 280px;
    /* 控制消息显示区域的最大宽度 */
    padding: 7px;
    background-color: white;
    border-radius: 4px;
    background-color: pink;
    text-align: left;
    font-size: 13px;
  }

  .content1 {
    float: left;
    margin-left: 55px;
    margin-top: -40px;
  }

  .content2 {
    float: right;
    margin-right: 55px;
    margin-top: -40px;
  }

  .emoji {
    position: absolute;
    height: 20vh;
    width: 20vw;
    overflow-y: scroll;
    z-index: 3;
    top: -21vh;

    li {
      display: block;
      float: left;
    }
  }

  ::-webkit-scrollbar {
    /*滚动条整体样式*/
    width: 8px;
    /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
  }

  ::-webkit-scrollbar-thumb {
    /*滚动条里面小方块*/
    border-radius: 10px;
    box-shadow: inset 0 0 5px rgba(97, 184, 179, 0.1);
    background: #ccc;
  }

  ::-webkit-scrollbar-track {
    /*滚动条里面轨道*/
    box-shadow: inset 0 0 5px rgba(87, 175, 187, 0.1);
    border-radius: 10px;
    background: #ededed;
  }

  .chart-from-btn {
    position: absolute;
    right: 10px;
    bottom: 0;
  }

  .chart-from-content {
    position: absolute;
    left: 0;
    width: 100%;
  }

  .chart-from-value {
    height: 14vh;
    width: 100%;
    float: left;
    background-color: rgba(240, 240, 240, 0.8);
    text-align: left;
    margin-top: 18px;
    overflow-y: scroll;
    border: none;
    display: flex;
    word-wrap: break-word;
    word-break: break-all;
    font-size: 15px;
    padding: 5px;
    padding-left: 10px;
    scrollbar-width: thin;
  }

  .chart-form {
    position: relative;
    width: 100%;
    height: 13vh;
    background-color: #fff;
    scrollbar-width: thin;

    img {
      width: 23px;
      height: 23px;
      margin-top: -4px;
    }

    .fun {
      float: left;
      position: relative;
      z-index: 2;
    }

    .btn1 {
      float: left;
    }
  }

  .user-list {
    width: 150px;
    background-color: rgba(153, 225, 229, 0.4);
    height: 83vh;
    scrollbar-width: thin;

    img {
      width: 55px;
      height: 55px;
      object-fit: cover;
    }

    .list1 {
      font-size: 14px;
      background-color: #b7eaed;
      margin: 0 auto;
      margin-top: 10px;
      margin-bottom: 10px;
      height: 35px;
      padding-top: 8px;
      border: 0.5px solid rgba(180, 180, 180, 0.3);
      border-left-color: deepskyblue;
      border-left: none;
      border-right: none;
    }

    .list {
      position: relative;
      text-align: left;
      padding-left: 10px;

      .chat-now {
        position: absolute;
        right: 10px;
        top: 20%;
        width: 35px;
        height: 35px;
        background: url("./../../assets/imgs/chatNow.png") no-repeat;
        background-size: 100%;
      }

      .point {
        left: 86%;
        bottom: -35%;
        width: 25px;
        height: 25px;
        border-radius: 50%;
        background-color: rgba(251, 175, 175, 0.8);
        position: relative;
      }

      .num {
        color: white;
        position: absolute;
        top: 15%;
        left: 35%;
      }
    }
  }

  .chatObj {
    font-size: 18px;
    height: 8vh;
    background-color: rgba(153, 225, 229, 0.7);
    color: #fff;
    padding-top: 15px;
  }

  .detail {
    position: relative;
    display: flex;
    flex-direction: column;
    width: 103%;
    overflow-y: hidden;

    .mark {
      width: 100%;
      height: 55vh;
      background-color: #fff;
      overflow-y: scroll;
      scrollbar-width: thin;

      img {
        width: 30px;
        height: 30px;
        margin-top: 10px;
      }
    }

    .chatV {
      background-color: pink;
    }

    .me,
    .it {
      width: 40%;
      height: 10%;
      align-self: flex-end;
      object-fit: cover;
      // background-color: pink;
    }

    .it {
      float: left;
    }

    .me {
      float: right;
      align-self: flex-start;
    }
  }
}
</style>
