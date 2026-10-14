<template>
  <div>
    <body id="top">

    <!-- Header -->
<!--    <header id="header" class="skel-layers-fixed" style="height: 100px;">-->
<!--      <h1 style="font-size: 30px;line-height: 100px"><a href="http://localhost:8080/">铜工艺品版权管理平台</a></h1>-->
<!--      <nav id="nav" style="font-size: 20px;line-height: 100px">-->
<!--        <ul>-->
<!--          <li><a href="http://localhost:8080/">首页</a></li>-->
<!--          <li><a href="http://localhost:8080/z1">铜艺生活</a></li>-->
<!--          <li><a href="http://localhost:8080/z2">青铜器仿制品</a></li>-->
<!--          <li><a href="http://localhost:8080/z3">佛事用品</a></li>-->
<!--          <li><a href="http://localhost:8080/z4">铜建筑艺术装饰</a></li>-->
<!--          <li><a href="http://localhost:8080/z5">铜雕塑</a></li>-->
<!--          <li><a href="http://localhost:8080/login1">管理员登录</a></li>-->
<!--          <li><a href="http://localhost:8080/login">用户登录</a></li>-->
<!--          <li><a href="http://localhost:8080/register">新用户注册</a></li>-->
<!--        </ul>-->
<!--      </nav>-->
<!--    </header>-->

        <header id="header" class="skel-layers-fixed" style="height: 100px;">
          <div>
            <h1 style="font-size: 30px;line-height: 100px"><a href="http://localhost:8080/">铜工艺品版权管理平台</a></h1>
            <nav id="nav" style="font-size: 20px;line-height: 100px">
              <ul>
                <li><a href="http://localhost:8080/home">首页</a></li>
                <li><a href="http://localhost:8080/manageuser/information">个人信息</a></li>
                <li><a href="http://localhost:8080/manageuser/up">版权登记</a></li>
                <li><a href="http://localhost:8080/manageuser/guanli">版权转让</a></li>
<!--                <li><a href="http://localhost:8080/manageuser/updata">版权修改</a></li>-->
                <li><a href="http://localhost:8080/manageuser/shensu">版权申诉</a></li>
                <li><a href="http://localhost:8080/manageuser/xinxi">版权信息</a></li>
                <li><a href="http://localhost:8080/manageuser/chaxun">版权查询</a></li>
                <li><a href="http://localhost:8080">退出</a></li>
              </ul>
            </nav>
          </div>
        </header>


    <!--     Banner -->
    <section style="background-color: #666666;margin-top: 50px">
      <!--      <h2 style="color: #eeeeee">铜工艺品版权管理系统</h2>-->
      <div>
        <el-carousel height="500px">
          <el-carousel-item  v-for="item in dataList" :key="item.id" align="center" :row-style="{height:'20px'}" style="font-size: 10px">
            <h3><img :src="item.src" alt="" style="height:500px;"  /></h3>
          </el-carousel-item>
        </el-carousel>
      </div>
      <!--      <div class="inner" style="margin-top: 20px">-->
      <!--        <p>输入作品名称查询信息</p>-->
      <!--        <ul class="actions">-->
      <!--          <el-input  size="medium" style="width: 500px;" placeholder="请输入名称" v-model="name" ></el-input>-->
      <!--          <el-button style="margin-left: 10px" type="primary" @click="getone" >查看</el-button>-->
      <!--        </ul>-->
      <!--      </div>-->
    </section>

    <el-dialog title="信息" :visible.sync="dialogFormVisible">
      <el-form label-width="120px" size="small">
        <el-form-item label="作者">
          <el-input v-model="form.username" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="作品名称">
          <el-input v-model="form.name" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-input v-model="form.type" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="上传时间">
          <el-input v-model="form.time" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-input v-model="key" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- One -->
    <section id="one" style="text-align: center;margin-top: 20px">
      <div class="inner" style="position: relative;">
        <b style="font-size: large">输入作品名称查询信息</b>
        <ul class="actions">
          <el-input  size="medium" style="width: 500px;" placeholder="请输入名称" v-model="name" ></el-input>
          <el-button style="margin-left: 10px" type="primary" @click="getone" >查看</el-button>
        </ul>
      </div>

      <div style="height: 380px;width: 1400px;margin: auto;color: #3f5efb">
        <h1>作品信息公示</h1>
        <el-table  ref="wgzp" :data="tableData" height="380px"  border @cell-mouse-enter="MouseEnter" @cell-mouse-leave="MouseLeave" @row-click="handle" style="font-size: 18px" :row-style="{height:'60px'}">
          <el-table-column prop="username" label="作者" width="140">
          </el-table-column>
          <el-table-column prop="name" label="作品名" width="160">
          </el-table-column>
          <el-table-column prop="type" label="作品类型">
          </el-table-column>
          <el-table-column prop="time" label="上传时间">
          </el-table-column>
        </el-table>
      </div>
    </section>

    <el-dialog title="信息" :visible.sync="dialogFormVisible1">
      <el-form label-width="120px" size="small">
        <el-form-item label="作者">
          <el-input v-model="form1.username" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="作品名称">
          <el-input v-model="form1.name" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-input v-model="form1.type" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="上传时间">
          <el-input v-model="form1.time" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
      </el-form>
    </el-dialog>



    <!-- Two -->
    <section id="two" class="wrapper style2" style="margin-top: 50px;text-align: center">
      <header class="major">
        <h2>版权管理</h2>
        <p>Copyright management</p>
      </header>
      <div style="display: inline-block;margin-left: 150px">
        <div class="c1" style="margin-right: 100px;">
          <div>
            <section class="special">
              <a href="http://localhost:8080/manageuser/up" ><img src="../assets/g/1.png" alt="" class="p" ></a>
              <h3>版权登记</h3>
            </section>
          </div>
        </div>
        <div class="c1" style="margin-right: 100px;">
          <div>
            <section class="special">
              <a href="http://localhost:8080//manageuser/guanli" ><img src="../assets/g/2.png" alt="" class="p" ></a>
              <h3>版权转让</h3>
            </section>
          </div>
        </div>
<!--        <div class="c1" style="margin-right: 100px;">-->
<!--          <div>-->
<!--            <section class="special">-->
<!--              <a href="http://localhost:8080/manageuser/updata" ><img src="../assets/g/updatat.png" alt="" class="p" ></a>-->
<!--              <h3>版权修改</h3>-->
<!--            </section>-->
<!--          </div>-->
<!--        </div>-->
        <div class="c1" style="margin-right: 100px;">
          <div>
            <section class="special">
              <a href="http://localhost:8080//manageuser/xinxi" ><img src="../assets/g/3.png" alt="" class="p" ></a>
              <h3>版权信息</h3>
            </section>
          </div>
        </div>
        <div class="c1" style="margin-right: 100px;">
          <div>
            <section class="special">
              <a href="http://localhost:8080//manageuser/chaxun" ><img src="../assets/g/4.png" alt="" class="p" ></a>
              <h3>版权查询</h3>
            </section>
          </div>
        </div>
        <div class="c1" style="margin-right: 100px;">
          <div>
            <section class="special">
              <a href="http://localhost:8080//manageuser/shensu" ><img src="../assets/g/5.png" alt="" class="p" ></a>
              <h3>版权申诉</h3>
            </section>
          </div>
        </div>
      </div>
    </section>

    <!-- Three -->
    <section id="three" class="wrapper style1">
      <header class="major">
        <h2>铜文化</h2>
        <p>Copper culture</p>
      </header>
      <div style="width: 1400px;margin: auto;text-align: center">
        <!--        <el-table  :data="tableData1" height="360px" :show-header="false" @row-click="handle1" style="font-size: 20px" :row-style="{height:'60px'}">-->
        <!--          <el-table-column prop="xinwen">-->
        <!--          </el-table-column>-->
        <!--        </el-table>-->
        <div style="display: inline-block;margin-right: 30px">
          <div class="c1" style="text-align: center">
            <div style="display: inline-block">
              <a href="http://localhost:8080/p1"><img src="../assets/p1.png" alt="" class="p"></a>
              <h3 class="h3">青铜文化研究</h3>
            </div>
          </div>
          <div class="c1" style="text-align: center">
            <div style="display: inline-block">
              <a href="http://localhost:8080/p2"><img src="../assets/p2.png" alt="" class="p" ></a>
              <h3 class="h3">铜文化的历史与传承</h3>
            </div>
          </div>
          <div class="c1" style="text-align: center">
            <div style="display: inline-block">
              <a href="http://localhost:8080/p3"><img src="../assets/p4.png" alt="" class="p" ></a>
              <h3 class="h3">铜工艺品加工制作工艺</h3>
            </div>
          </div>
          <div class="c1" style="text-align: center">
            <div style="display: inline-block">
              <a href="http://localhost:8080/p4"><img src="../assets/p3.png" alt="" class="p" ></a>
              <h3 class="h3">写真铜艺</h3>
            </div>
          </div>
          <div class="c1" style="text-align: center">
            <div style="display: inline-block">
              <a href="http://localhost:8080/p5"><img src="../assets/p5.png" alt="" class="p" ></a>
              <h3 class="h3">皖南古铜矿遗址</h3>
            </div>
          </div>
        </div>
      </div>

    </section>

    <section id="two" class="wrapper style2" style="margin-top: 50px;text-align: center">
      <header class="major">
        <h2>铜工艺品</h2>
        <p>Copper crafts</p>
      </header>
      <div style="display: inline-block;margin-left: 100px">
        <div class="c1">
          <div>
            <section class="special">
              <a href="http://localhost:8080/z1" ><img src="../assets/z1.png" alt="" class="p" ></a>
              <h3>铜艺生活</h3>
              <ul class="actions">
                <li><a href="http://localhost:8080/z1" class="button alt">More</a></li>
              </ul>
            </section>
          </div>
        </div>
        <div class="c1">
          <div>
            <section class="special">
              <a href="http://localhost:8080/z2" ><img src="../assets/z2.png" alt="" class="p" ></a>
              <h3>青铜器仿制品</h3>
              <ul class="actions">
                <li><a href="http://localhost:8080/z2" class="button alt">More</a></li>
              </ul>
            </section>
          </div>
        </div>
        <div class="c1">
          <div>
            <section class="special">
              <a href="http://localhost:8080/z3" ><img src="../assets/z3.png" alt="" class="p" ></a>
              <h3>佛事用品</h3>
              <ul class="actions">
                <li><a href="http://localhost:8080/z3" class="button alt">More</a></li>
              </ul>
            </section>
          </div>
        </div>
        <div class="c1">
          <div>
            <section class="special">
              <a href="http://localhost:8080/z4" ><img src="../assets/z4.png" alt="" class="p" ></a>
              <h3>铜建筑艺术装饰</h3>
              <ul class="actions">
                <li><a href="http://localhost:8080/z4" class="button alt">More</a></li>
              </ul>
            </section>
          </div>
        </div>
        <div class="c1">
          <div>
            <section class="special">
              <a href="http://localhost:8080/z5" ><img src="../assets/z5.png" alt="" class="p" ></a>
              <h3>铜雕塑</h3>
              <ul class="actions">
                <li><a href="http://localhost:8080/z5s" class="button alt">More</a></li>
              </ul>
            </section>
          </div>
        </div>
      </div>

    </section>

    <section id="three" class="wrapper style1" style="text-align: center">
      <header class="major" style=" margin-bottom: 0px;">
        <h2>本平台与其他版权管理平台的区别</h2>
        <p>System differences</p>
      </header>

      <div style="display: inline-block;text-align: center">

        <div style="display: inline-block" class="qubie">
          <div>
            <el-button type="primary" style="width: 300px;height: 50px;font-size: 30px" @click="xianshi">铜文化宣传</el-button>
            <p :class="one" style="font-size: 20px;width: 310px;text-align: left;text-indent: 2em;margin-top: 20px">本平台调研了大量的铜文化资料，并在本系统中进行宣传。</p>
          </div>
        </div>

        <div style="display: inline-block" class="qubie">
          <div>
            <el-button type="primary" style="width: 300px;height: 50px;font-size: 30px" @click="xianshi1">铜工艺品展示</el-button>
            <p :class="one1" style="font-size: 20px;width: 310px;text-align: left;text-indent: 2em;margin-top: 20px">本平台调研了大量的铜工艺品资料，并采集了大量的铜工艺品图片与介绍，放在系统中进行展示，展示的所有数据均真实存在。</p>
          </div>
        </div>

        <div style="display: inline-block" class="qubie">
          <div>
            <el-button type="primary" style="width: 300px;height: 50px;font-size: 30px" @click="xianshi2">版权信息安全存储</el-button>
            <p :class="one2" style="font-size: 20px;width: 310px;text-align: left;text-indent: 2em;margin-top: 20px">本平台以铜工艺品信息的安全存储与加密为核心，采用多种技术相结合，保障电子版权数据的安全存储与共享。</p>
          </div>
        </div>

        <div style="display: inline-block" class="qubie">
          <div>
            <el-button type="primary" style="width: 300px;height: 50px;font-size: 30px" @click="xianshi3">区块链技术</el-button>
            <p :class="one3" style="font-size: 20px;width: 310px;text-align: left;text-indent: 2em;margin-top: 20px">本平台运用区块链技术，若发生版权纠纷便于进行溯源以及追责，实现数据的完整性、不可篡改性和抗 DDOS 攻击。</p>
          </div>
        </div>


      </div>

    </section>

    <!--    <section id="three" class="wrapper style1" style="text-align: center">-->
    <!--      <div style="display: inline-block">-->
    <!--        <el-button @click="drawer = true" type="text">-->
    <!--          <img src="../assets/banquan.png" alt="" style="height: 100px">-->
    <!--        </el-button>-->
    <!--        <h2>工艺品版权保护</h2>-->
    <!--        <el-drawer-->
    <!--            title="我是标题"-->
    <!--            :visible.sync="drawer"-->
    <!--            :with-header="false">-->
    <!--          <div style="margin-top: 120px;text-indent: 2em;color: #000;text-align: left">-->
    <!--            <h3>工艺品版权保护</h3>-->
    <!--            <h4>创作了一个作品，怎样确保自己有版权呢？</h4>-->
    <!--            <p>中国公民、法人或者其他组织的作品，不论是否发表，依法享有版权。外国人、无国籍人的作品根据其作者所属国或者经常居住地国同中国签订的协议或者共同参加的国际条约享有的版权，受本法保护。</p>-->
    <!--            <p>艺术作品自创作完成之日起即依法享有版权，不需要特别程序。</p>-->
    <!--            <h4>如果两个作品存在相似之处，如何界定侵权呢？</h4>-->
    <!--            <p>著作权侵权认定中，被指控作品是剽窃的还是原创的，是界定侵权与否的关键。</p>-->
    <!--            <p>《保护文学艺术作品的伯尔尼公约》及各国著作权法均规定了享有著作权的作品必须具备的实质性条件是作品具有独创性，包括独立创作和一定的创造性。独创性即原创性，表明作品是作者独立创作，是作者思想、观点的表达，融入了作者的选择、安排、取舍等个人印记，排除了抄袭剽窃的可能；创造性是指新作品不同于已有作品的表达形式，与已有作品相比存在实质性差异，而不论新作品价值大小、创作水平的高低及社会影响力如何。著作权法并不要求作品是首创的，但必须是原创的，新作品与已有作品的表达形式不同。即使是不同作者就同一题材创作，只要具有不同的表达形式，体现独创性，就各自享有独立的著作权。</p>-->
    <!--            <p>在我国理论及司法实践中，采用美国判例中的“接触＋实质性相似”来认定侵权行为。“接触”是以被告曾实际见过或可能见过原告的作品来判断。“实质性相似”指侵权作品与他人作品实质上相同或相似，且所占比例较大，不同之处仅仅是改头换面的添加，且又有其他证据证明行为人曾经接触过他人作品，则基本可以认定作品缺乏独创性，实质性相似。</p>-->
    <!--            <p>按照侵权法理论，构成侵权需要具备“四要件”，该理论在著作权侵权认定中依然适用。</p>-->
    <!--            <p>剽窃侵权四要件： ①有剽窃他人作品的事实； ②剽窃行为在性质上侵害了他人的著作人身权（署名权）和财产权（复制权）； ③侵权事实与损害后果具有因果关系； ④行为人主观上存在过错（行为人往往明知他人已经创作了相关作品，而故意从事剽窃行为，甚至将剽窃作品投入市场销售或用于发表以牟取非法利益，说明其在主观上具有侵权的故意）。在“技术”层面上还应针对美术作品的具体情况来分析对比，甚至邀请相关领域专家作出鉴定。</p>-->
    <!--            <h4>艺术作品出售后，其图案和印花版权归属是哪一方？艺术家还可以用图案印花做衍生品吗？</h4>-->
    <!--            <p>艺术作品出售后，原则上除发表权、署名权、修改权和保护作品完整权归作者以外，其他权利根据《转让合同》的约定来确定权利归属。双方可以《转让合同》中具体协商确定全部或部分转让权利。美术作品原件的展览权由原件所有人享有。</p>-->
    <!--            <h4>艺术作品如何合理合法定价？</h4>-->
    <!--            <p>艺术作品定价包括版权转让的定价和许可使用的定价。</p>-->
    <!--            <p>转让作品的定价是根据作者的市场行情及双方协商确定的，部分转让和全部转让的价格也有所不同，转让的权利种类和地域范围不同，价格也不相同。 许可使用作品的付酬标准可以由当事人约定，也可以按照国务院版权行政管理部门会同有关部门制定的付酬标准支付报酬。当事人约定不明确的，按照国务院版权行政管理部门会同有关部门制定的付酬标准支付报酬。许可使用的范围、种类、时间不同，付酬标准也不同。</p>-->
    <!--            <h4>想做一些致敬大师的作品，版权如何界定？</h4>-->
    <!--            <p>《著作权法》规定“改编、翻译、注释、整理已有作品而产生的作品，其版权由改编、翻译、注释、整理人享有，但行使版权时不得侵犯原作品的版权。” 所以首先要尊重大师的版权，大师对其作品享有发表权、署名权、修改权和保护作品完整权，即保护作品不受歪曲、篡改的权利。且上述权利被永久保护。 作品的发表权及财产权利的保护期为作者终生及其死亡后五十年，截止于作者死亡后第五十年的12月31日；如果是合作作品，截止于最后死亡的作者死亡后第五十年的12月31日。在保护期内使用大师的作品，应取得权利人的许可。</p>-->
    <!--            <em style="float: left">摘自搜狐</em>-->
    <!--          </div>-->
    <!--        </el-drawer>-->
    <!--      </div>-->

    <!--      <div style="display: inline-block;margin-left: 50px;text-indent: 2em;color: #000;">-->
    <!--        <el-button @click="drawer1 = true" type="text">-->
    <!--          <img src="../assets/lian.png" alt="" style="height: 100px">-->
    <!--        </el-button>-->
    <!--        <h2>区块链技术</h2>-->
    <!--        <el-drawer-->
    <!--            title="我是标题"-->
    <!--            :visible.sync="drawer1"-->
    <!--            :with-header="false">-->
    <!--          <div style="margin-top: 120px;text-align: left">-->
    <!--            <h3>区块链技术</h3>-->
    <!--            <p>区块链，就是一个又一个区块组成的链条。每一个区块中保存了一定的信息，它们按照各自产生的时间顺序连接成链条。这个链条被保存在所有的服务器中，只要整个系统中有一台服务器可以工作，整条区块链就是安全的。这些服务器在区块链系统中被称为节点，它们为整个区块链系统提供存储空间和算力支持。如果要修改区块链中的信息，必须征得半数以上节点的同意并修改所有节点中的信息，而这些节点通常掌握在不同的主体手中，因此篡改区块链中的信息是一件极其困难的事。相比于传统的网络，区块链具有两大核心特点：一是数据难以篡改、二是去中心化。基于这两个特点，区块链所记录的信息更加真实可靠，可以帮助解决人们互不信任的问题。</p>-->
    <!--            <em style="float: left">摘自百度百科</em>-->
    <!--          </div>-->
    <!--        </el-drawer>-->
    <!--      </div>-->
    <!--    </section>-->

    <!-- Footer -->

    <section id="two" class="wrapper style2" style="margin-top: 50px;text-align: center">
    </section>

    </body>
  </div>
</template>

<script>
export default {
  name: "Homehtml",
  data() {
    return{
      name:"",
      form: {},
      form1: {},
      interval:'',
      dialogFormVisible: false,
      key:"",
      tableData:[],
      dialogFormVisible1: false,
      showheader: false,
      drawer: false,
      drawer1: false,
      one: "one",
      one1: "one",
      one2: "one",
      one3: "one",
      test: false,
      test1: false,
      test2: false,
      test3: false,
      dataList: [
        {id: 1, src: require("@/assets/picture/pic03.png")},
        {id: 2, src: require("@/assets/picture/pic05.png")},
        {id: 3, src: require("@/assets/picture/pic07.jpg")},
      ]
    }
  },
  created() {
    this.request.get("http://localhost:9090/banquan").then(res => {
      this.tableData = res
    })
  },
  methods: {
    getone() {
      this.request.get("http://localhost:9090/banquan/name/" + this.name).then(res => {
        // console.log(res.phone)
        this.form = res
        this.dialogFormVisible =true
        if(res.url !== null) {
          this.key = "已入库"
        }else {
          this.key = "审核中"
        }
      })
    },
    xianshi() {
      this.test = !this.test
      if(this.test){
        this.one = "one1"
      }else {
        this.one = "one"
      }
    },
    xianshi1() {
      this.test1 = !this.test1
      if(this.test1){
        this.one1 = "one1"
      }else {
        this.one1 = "one"
      }
    },
    xianshi2() {
      this.test2 = !this.test2
      if(this.test2){
        this.one2 = "one1"
      }else {
        this.one2 = "one"
      }
    },
    xianshi3() {
      this.test3 = !this.test3
      if(this.test3){
        this.one3 = "one1"
      }else {
        this.one3 = "one"
      }
    },
    scroll(){
      let maxHeight=this.$refs.wgzp.$el.querySelectorAll ('.el-table__body')[0].offsetHeight;
      let clientHeight=this.$refs.wgzp.bodyWrapper.clientHeight;
      if(Math.abs(this.$refs.wgzp.bodyWrapper.scrollTop-(maxHeight-clientHeight))<5){ //预留5像素误差
        this.$refs.wgzp.bodyWrapper.scrollTop=0;
      }else{
        this.$refs.wgzp.bodyWrapper.scrollTop+=60;//每一行表格的高度，每秒滚一行
      }
    },
    MouseEnter(){//鼠标移入停止滚动
      clearInterval(this.interval);
    },
    MouseLeave(){//鼠标离开继续滚动
      this.interval=setInterval(this.scroll,1000);
    },
    handle(row, event, column) {
      this.form1 = row
      this.dialogFormVisible1 = true
    },
  },
  mounted() {
    this.interval=setInterval(this.scroll,1000);
    //请求后台接口获取列表数据
    /*this.getList().then((res)=>{
      this.tableData = res.list;
    });*/
  },
  beforeDestroy(){
    clearInterval(this.interval);
  }
}
</script>

<style>
/*@import "../../public/static1/css/demo.css";*/
@import "../../public/static1/css/flutter.css";
@import '../../public/static/css/skel.css' ;
@import '../../public/static/css/style.css' ;
@import '../../public/static/css/style-xlarge.css' ;
::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;height: 0;
}
.el-carousel__item h3 {
  /*color: #475669;*/
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.c1{
  display: inline-block;
}

.p{
  height: 120px;
}

.major{
  font-size: 25px;
  margin-bottom: 0px;
}

.c1{
  font-size: 20px;
  font-weight: 400;
  margin-right: 40px;
}

.special{
  margin-right: 50px;
}

.one{
  display: none;
  background-color: #ffce44;
  width: 150px;
  height: 150px;
  animation:fadenum 5s 1;
}

@keyframes one{
  0%{opacity: 0;}
  100%{opacity: 1;}
}

.one1{
  display: block;
}

.qubie{
  margin-right: 20px;
  background-color: #66b1ff;
  color: black;
}

.h3{
  font-weight: 400;
}

</style>
