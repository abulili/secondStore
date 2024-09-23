<template>
  <div>
    <el-container>
      <el-main>
        <el-alert title="此地址超出当前校园范围，请注意甄别！" type="warning" show-icon
          style="z-index:10;position:absolute;width: 35%;left: 14%;" v-show="locationW">
        </el-alert>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-row>
              <!-- 这是图片的缩略图 -->
              <el-col :span="5">
                <div>
                  <img v-if="storeItem.spic" class="small-imgs-lists" :src="'data:image/png;base64,' + storeItem.spic"
                    alt="" @click="mapShow = false" />
                  <img v-else class="small-imgs-lists" src="./../../assets/imgs/noImg.png" alt=""
                    @click="mapShow = false" />
                </div>
                <!-- <div> <img class="small-imgs-lists" src="./../../assets/logo.png" alt=""></div>
                                <div> <img class="small-imgs-lists" src="./../../assets/logo.png" alt=""></div>
                                <div> <img class="small-imgs-lists" src="./../../assets/logo.png" alt=""></div>
                                <div> <img class="small-imgs-lists" src="./../../assets/logo.png" alt=""></div> -->
                <div style="">
                  <img class="small-imgs-lists" src="./../../assets/imgs/cation.png" alt="" @click="mapShow = true"
                    style="object-fit: scale-down; cursor: pointer" />
                </div>
                <div style="position: absolute; top: 60%; left: 5%">
                  <el-button type="info" @click="report('store')">举报</el-button>
                </div>
              </el-col>
              <el-col :span="19">
                <div class="photo">
                  <img v-if="storeItem.spic && !mapShow" class="small-imgs-lists"
                    :src="'data:image/png;base64,' + storeItem.spic" alt="" style="width: 400px; height: 400px" />
                  <img v-else-if="!mapShow" class="big-img" src="./../../assets/imgs/noImg.png" alt=""
                    style="width: 330px;height: auto;position: relative;left: px;left: 1px;top: 1px;}" />
                  <div class="bmap-wrapper" v-if="mapShow">
                    <el-bmap class="bmap-box" :zoom="zoom" :center="center" :vid="'bmap-vue'" :tilt="73" :heading="90">
                      <!-- <el-bmap-info-window :visible="current.visible" :position="current.position"
                                                :content="current.content"></el-bmap-info-window> -->
                      <el-bmapv-view>
                        <el-bmapv-icon-layer :icon="icon" :width="width" :height="height" :data="datas"
                          :enable-picked="true"></el-bmapv-icon-layer>
                      </el-bmapv-view>
                    </el-bmap>
                  </div>
                </div>
                <!-- <div>地址： {{ location }}</div> -->
                <div style="margin-top: 15px" v-if="storeItem.isdelete">
                  <!-- <el-button>举报</el-button> -->
                  <el-button @click="addStar" style="background-color: #f3e8c6; border-color: #f3e8c6; color: #fff">{{
            starInfo }}</el-button>
                  <el-button @click="addCar"
                    style="background-color: #f2c6b4; border-color: #f2c6b4; color: #fff">加入购物车</el-button>
                  <el-button @click="addBought"
                    style="background-color: #fbafaf; border-color: #fbafaf; color: #fff">购买</el-button>
                </div>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="12">
            <div style="position: relative">
              <div style="left: 0; position: absolute; font-size: 14px">
                <img class="small-imgs" v-if="saleUser.uavatar" style="width: 70px; height: 70px; object-fit: cover"
                  :src="'data:image/png;base64,' + saleUser.uavatar" alt="" />
                <img class="small-imgs" v-else style="width: 70px; height: 70px; object-fit: cover"
                  src="https://img1.baidu.com/it/u=2893147358,3393430132&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
                  alt="" />

                <!-- 卖家名（卖家个人信息,限制一下名称长短） -->
                <font style="margin-left: 10px; font-size: 20px">{{
            saleUser.uname
          }}</font>
              </div>
              <div style="position: absolute; right: 30px">
                <el-button size="small" @click="saleView">ta的主页</el-button>
              </div>
            </div>
            <div class="des">
              <!-- <div>
                                <h2>地点</h2>
                                <div style="font-size: 14px;">这里应该放地图</div>
                            </div> -->
              <div style="margin-top: 20px">
                <h1>{{ storeItem.stitle }}</h1>
              </div>
              <el-divider></el-divider>
              <div style="margin-top: 15px">
                <h2>商品描述</h2>
                <div style="margin-top: 8px; white-space: pre-wrap; font-size: 15px">
                  {{ storeItem.scontent }}
                  <!-- 七成新，有磨损，不接受砍价。拍下后与我联系具体交易时间，地点如左图。左边一个点击出图片，另一个显示地图 -->
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-main>
      <el-footer style="margin-top: 10px">
        <h2>评论</h2>
        <div v-if="storeItem.sid">
          <el-row :gutter="10">
            <el-col :span="2">
              <!-- ./../../assets/logo.png -->
              <!-- <img class="small-imgs"
                                src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F11d6992a-d430-46dd-80a3-bccb3f1e05fe%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1712458532&t=8a7f8b8daf249b6aa13b39852f305454"
                                alt=""> -->
              <img v-if="userInfo.uavatar" class="small-imgs" :src="'data:image/png;base64,' + userInfo.uavatar"
                alt="" />
              <img v-else class="small-imgs"
                src="https://img1.baidu.com/it/u=2893147358,3393430132&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
                alt="" />
            </el-col>
            <el-col :span="19"><el-input v-model="input" placeholder="请输入内容"></el-input></el-col>
            <el-col :span="3"><el-button @click="addCommment">发送</el-button></el-col>
          </el-row>
        </div>
        <div class="rows" v-for="(item, index) in comments" :key="index">
          <el-row :gutter="10">
            <el-col :span="2">
              <img v-if="item.user.uavatar" class="small-imgs" :src="'data:image/png;base64,' + item.user.uavatar"
                alt="" />
              <!-- <img v-else class="small-imgs" :src="'data:image/png;base64,' + item.user.uavatar"
                                src="https://img0.baidu.com/it/u=1954329553,3544794056&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
                                alt="" /> -->
              <img v-else class="small-imgs"
                src="https://img1.baidu.com/it/u=2893147358,3393430132&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
                alt="" />
            </el-col>
            <el-col :span="20" style="text-align: left; font-size: 14px; padding-top: 0px">
              <font style="color: rgba(17, 17, 17, 0.5)">{{ item.user.uname }}</font>
              <div>{{ item.comment.ccontent }}</div>
            </el-col>
            <el-col :span="2"><el-button size="mini" type="info" style="margin-top: 10px"
                @click="report('comment', item)">举报</el-button></el-col>
          </el-row>
          <!-- <el-row :gutter="10">
                        <el-col :span="2">
                            <img class="small-imgs"
                                src="https://img2.baidu.com/it/u=685632081,4254263062&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=600"
                                alt="">
                        </el-col>
                        <el-col :span="22" style="text-align: left; font-size: 14px;padding-top: 9px;">
                            <font style="color: rgba(17,17,17,0.5)">cccc</font>
                            <div>这本书太老了感觉买了没用,我瞎说的我也不知道</div>
                        </el-col>
                    </el-row> -->
        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";
import { _serachUid } from "@/api/user";
import { _CommentUser, _addComment } from "@/api/comment";
import { _storeStar, _addStar, _delStar } from "@/api/star";
import { _addCar } from "@/api/car";
import { _addOrder } from "@/api/order";
import { _addReport } from "@/api/report";
import { _listId, _updateStore, _getLDB } from "@/api/index";
import Cookies from 'js-cookie'
export default {
  data() {
    return {
      storeItem: {},
      mapShow: false,
      location: "",
      input: "",
      zoom: 19,
      center: [103.738152, 29.56574],
      icon: "/src/assets/imgs/location.png",
      width: 24,
      height: 40,
      datas: [
        {
          geometry: {
            type: "Point",
            coordinates: [103.738152, 29.56574],
          },
          properties: {
            icon: "/src/assets/imgs/location.png",
          },
        },
      ],
      current: {
        position: [103.738152, 29.56574],
        visible: false,
        // content: 'hello world'
      },
      saleUser: {},
      comments: [],
      starInfo: "加入收藏",
      starStatus: {},
      userInfo: [],
      locationW: false
    };
  },
  methods: {
    IsPointOnLine(x, y, cx1, cy1, cx2, cy2) {
      // let temp = (cx1 - x) * (cy2 - y) - (cx2 - x) * (cy1 - y)
      if (((x - cx1) * (x - cx2) <= 0) && ((y - cy1) * (y - cy2) <= 0)) {
        return true
      }
      return false
    },
    // 两线段是否相交
    IsIntersect(cx1, cy1, cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y) {
      let d = (cx2 - cx1) * (linePoint2y - linePoint1y) - (cy2 - cy1) * (linePoint2x - linePoint1x);
      if (d != 0) {
        let r = ((cy1 - linePoint1y) * (linePoint2x - linePoint1x) - (cx1 - linePoint1x) * (linePoint2y - linePoint1y)) / d;
        let s = ((cy1 - linePoint1y) * (cx2 - cx1) - (cx1 - linePoint1x) * (cy2 - cy1)) / d;
        if ((r >= 0) && (r <= 1) && (s >= 0) && (s <= 1)) {
          return true
        }
      }
      return false
    },
    PointCheck(p, arr) {
      let count = false

      let minX = 180.0
      for (let i = 0; i < arr.legth; ++i) {
        if (arr[i][0] < minX) {
          minX = arr[i][0]
        }
      }

      let px = p[0]
      let py = p[1]

      let linePoint1x = px
      let linePoint1y = py
      let linePoint2x = minX - 0.1
      let linePoint2y = py

      for (let i = 0; i < arr.length - 1; ++i) {
        let cx1 = arr[i][0]
        let cy1 = arr[i][1]
        let cx2 = arr[i + 1][0]
        let cy2 = arr[i + 1][1]

        let k1;
        let k2;
        if (i > 0) {
          k1 = (arr[i - 1][1] - cy1) / (arr[i - 1][0] - cx1)
          k2 = (cy2 - cy1) / (cx2 - cx1)
        }

        if (k1 != k2 && this.IsPointOnLine(px, py, cx1, cy1, cx2, cy2)) {
          return true;
        }
        // 假设是两个三角形纠缠的中间，要确保他们的斜率是不一样的，一样的就需要反方向判定了
        // 多边形的一个顶点在射线上，且该顶点是上顶点(y值较高)，算一个交点
        if (k1 != k2 && this.IsPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
          if (cy1 > cy2) {
            count = !count;
          }
        }
        // 多边形的一个顶点在射线上，且该顶点是上顶点(y值较高)，算一个交点
        else if (k1 != k2 && this.IsPointOnLine(cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
          if (cy2 > cy1) {
            count = !count;
          }
        }
        // 已经排除平行的情况，其他相交的都算一个交点
        else if (k1 != k2 && this.IsIntersect(cx1, cy1, cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
          count = !count;
        }

      }
      return count
    },
    async getLDB() {
      try {
        let res = await _getLDB()
        if (res.status == 200) {
          res = res.data
          console.log('getLDb', res)
          let p = [Number(this.storeItem.slng), Number(this.storeItem.slat)]
          console.log('p', this.storeItem)
          let str = res[0].scontent
          const pairs = str.split(',');
          const coordinates = [];
          for (let i = 0; i < pairs.length; i += 2) {
            const longitude = parseFloat(pairs[i]);
            const latitude = parseFloat(pairs[i + 1]);
            coordinates.push([longitude, latitude]);
          }
          if (!this.PointCheck(p, coordinates)) {
            this.locationW = true
          }
        }
      } catch (error) {
        console.log(error)
      }
    },
    report(type, item) {
      console.log("?");
      this.$confirm("确认举报, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.addReport(type, item);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    async addReport(type, item) {
      try {
        let data;
        if (type == "store") {
          data = {
            sid: this.storeItem.sid,
            cid: -1,
            isdeal: "0",
          };
        } else {
          data = {
            cid: item.comment.cid,
            sid: -1,
            isdeal: "0",
          };
        }
        let res = await _addReport(data);
        if (res.status == 200) {
          if (res.data) {
            this.$message({
              type: "success",
              message: "举报成功!",
            });
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    saleView() {
      // this.$store.state.saleView = this.saleUser
      this.$store.commit("setSaleView", this.saleUser);
      setTimeout(() => {
        this.$router.push("/userView");
      }, 100);
    },
    addBought() {
      let uid = Cookies.get('uid')
      // console.log(uid)
      if (!uid) {
        this.$message({
          type: "wrong",
          message: "用户未登录"
        })
      }
      else {
        this.$confirm("确认购买?", "", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            this.restNum();
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消购买",
            });
          });
      }
    },
    async restNum() {
      try {
        let res = await _listId({
          sid: this.storeItem.sid,
        });
        // console.log('restNum', res)
        if (res.status == 200) {
          let totalNum = res.data;
          totalNum = totalNum[0].snum;
          if (totalNum == 0) {
            // console.log('此商品没了')
            this.$message.error("此商品暂已售完");
            setTimeout(() => {
              this.$router.push("/index");
            }, 1000);
          } else {
            this.changeStoreNum(totalNum - 1);
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    async changeStoreNum(num) {
      try {
        let isdelete = "0";
        if (num == 0) {
          isdelete = "1";
        }
        let res = await _updateStore({
          sid: this.storeItem.sid,
          stitle: this.storeItem.stitle,
          scontent: this.storeItem.scontent,
          snum: num,
          suid: this.storeItem.suid,
          sprice: this.storeItem.sprice,
          slng: this.storeItem.slng,
          slat: this.storeItem.slat,
          isdelete: isdelete,
        });
        if (res.status == 200) {
          if (res.data) {
            this.addBoughtBeta();
          } else {
            this.$message.error("购买失败");
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    async addBoughtBeta() {
      try {
        // 添加当前商品信息到bought(购买) 啊这不是订单吗，算了不想改了
        let data = {
          uid: this.storeItem.suid,
          uid2: this.$store.state.users.userInfo.uid,
          sid: this.storeItem.sid,
          bnum: 1,
        };
        let res = await _addOrder(data);
        if (res.status == 200) {
          if (res.data) {
            this.$message({
              message: "成功购买",
              type: "success",
            });
            setTimeout(() => {
              this.$router.push("/selfchange");
            }, 500);
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    async delStar() {
      // 其实这里可以防抖，先不管了
      try {
        this.findStar();
        console.log(this.starStatus);
        let res = await _delStar(this.starStatus[0]);
        if (res.status == 200) {
          if (res.data) {
            this.$message({
              message: "成功取消收藏",
              type: "success",
            });
            this.starInfo = "加入收藏";
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    async addStar() {
      try {
        // 添加当前商品信息到star
        // 其实可以update的，如果之前isdelete为1，先不管了
        if (this.starInfo == "加入收藏") {
          let data = {
            uid: this.$store.state.users.userInfo.uid,
            sid: this.storeItem.sid,
          };
          let res = await _addStar(data);
          if (res.status == 200) {
            if (res.data) {
              this.$message({
                message: "收藏成功",
                type: "success",
              });
              this.starInfo = "取消收藏";
            }
          }
        } else {
          this.delStar();
        }
      } catch (error) {
        console.log(error);
      }
    },
    async addCar() {
      try {
        // 添加当前商品信息到car
        let data = {
          uid: this.$store.state.users.userInfo.uid,
          sid: this.storeItem.sid,
          snum: 1,
        };
        let res = await _addCar(data);
        if (res.status == 200) {
          if (res.data) {
            this.$message({
              message: "购物车成功添加1件商品",
              type: "success",
            });
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    async getUser() {
      try {
        // 查询卖家信息
        let data = {
          uid: this.storeItem.suid,
        };
        let res = await _serachUid(data);
        if (res.status == 200) {
          this.saleUser = res.data;
          this.saleUser = this.saleUser[0];
          // console.log(this.saleUser)
        }
      } catch (error) {
        console.log(error);
      }
    },
    async getComments() {
      try {
        // 查询此商品下评论
        let sid = this.storeItem.sid;
        if (sid == "" || sid == undefined || sid == null) {
          sid = this.storeItem.sidstore;
        }
        let data = {
          sid: sid,
        };
        let res = await _CommentUser(data);
        if (res.status == 200) {
          this.comments = [];
          res = res.data;
          for (let i = 0; i < res.length; ++i) {
            this.comments.push(res[i]);
          }
          // console.log(this.commentUsers, this.comments)
        }
      } catch (error) {
        console.log(error);
      }
    },
    async addCommment() {
      try {
        // 此用户添加评论
        let data = {
          sid: this.storeItem.sid,
          uid: this.storeItem.suid,
          suid: this.$store.state.users.userInfo.uid,
          ccontent: this.input,
        };
        console.log(data);
        let res = await _addComment(data);
        console.log(res);
        if (res.status == 200) {
          if (res.data) {
            this.$message({
              message: "评论成功",
              type: "success",
            });
            this.input = "";
            this.getComments();
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    async findStar() {
      try {
        console.log("this.storeItem.", this.storeItem);
        let sid = this.storeItem.sid;
        if (sid == "" || sid == undefined || sid == null) {
          sid = this.storeItem.sidstore;
        }
        let data = {
          uid: this.$store.state.users.userInfo.uid,
          sid: sid,
        };
        let res = await _storeStar(data);
        if (res.status == 200) {
          if (res.data.length == 0) {
            this.starInfo = "加入收藏";
          } else {
            this.starInfo = "取消收藏";
            this.starStatus = res.data;
            console.log("取消收藏", res);
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
  },
  mounted() {
    this.storeItem = this.$store.state.users.storeDetail;
    this.userInfo = this.$store.state.users.userInfo;
    // console.log('storeItem', this.storeItem)
    this.getUser(this.storeItem.suid);
    // 查收藏没有
    this.findStar();
    this.center = [Number(this.storeItem.slng), Number(this.storeItem.slat)];
    this.datas[0].geometry.coordinates = [this.storeItem.slng, this.storeItem.slat];
    this.current.position = [this.storeItem.slng, this.storeItem.slat];
    // this.myGeo(this.$store.state.storeDetail.slng, this.$store.state.storeDetail.slat) 只有后端去做，同源策略
    this.getComments();
    this.getLDB()
  },
};
</script>

<style lang="scss" scoped>
.el-container {
  width: 90vw;
  margin: 0 auto;
  margin-top: 13px;
  // background-color: pink;
}

.photo {
  position: relative;
  height: 400px;
  width: 500px;

  .big-img {
    position: relative;
    height: 400px;
    width: 400px;
    left: -88px;
    top: -75px;
  }
}

.bmap-wrapper {
  position: absolute;
  height: 500px;
  width: 500px;
  top: 0px;
  // img {
  //     width: 100%;
  // }
}

.rows {
  .el-row {
    margin-top: 10px;
  }
}

.des {
  height: 50vh;
  max-height: 40vh;
  overflow-y: scroll;
  margin-top: 10vh;
  text-align: left;
}

.small-imgs {
  width: 40px;
  height: 40px;
  object-fit: cover;
}

.small-imgs-lists {
  margin-top: 10px;
  width: 100px;
  height: 100px;
  // width: 100%;
  object-fit: contain;
  cursor: pointer;
}
</style>
