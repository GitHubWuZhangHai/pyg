//页面初始化完成后，调用此逻辑
window.onload=function () {

    var app = new Vue({
        //把页面上id为app的内容交给vue解析
        el: "#app",
        //变量声明
        data: {
            //数据列表
            list: [],
            //总页数
            pages: 0,
            //当前页
            pageNo: 1,
            //每页查询的记录数
            pageSize: 10,
            //表单提交对象
            entity: {},
            //删除的id列表
            ids: [],
            //查询条件
            searchEntity: {}
        },
        //包装页面逻辑代码
        methods: {
            //查询所有品牌
            findAll: function () {
                axios.get("/brand/findAll.do").then(function (response) {
                    app.list = response.data;
                })
            },

            //分页查询品牌
            findPage: function (page) {
                //axios.get("/brand/findPage.do?pageNo=" + page+ "&pageSize=" + this.pageSize).then(function (response) {
                axios.post("/brand/findPage.do?pageNo=" + page + "&pageSize=" + this.pageSize, this.searchEntity).then(function (response) {
                    //更新数据列表
                    app.list = response.data.rows;
                    //更新总页数
                    app.pages = response.data.pages;
                    //更新当前页变量
                    app.pageNo = page;
                })
            },
            //保存品牌
            add: function () {
                let url = "/brand/add.do";
                if (this.entity.id != null) {
                    url = "/brand/update.do";
                }
                axios.post(url, this.entity).then(function (response) {
                    //...处理结果
                    if (response.data.success) {
                        //刷新当前页
                        app.findPage(app.pageNo);

                        //查询数据
                        //app.findPage(1);
                        //让分页标签跳转到某个页
                        //app.goPage(1);
                    } else {
                        alert(response.data.message);
                    }
                })
            },
            //让分页插件跳转到某页
            goPage: function (page) {
                app.$children[0].goPage(page);
            },
            //根据id查询品牌
            getById: function (id) {
                axios.get("/brand/getById.do?id=" + id).then(function (response) {
                    app.entity = response.data;
                })
            },
            //批量删除
            dele: function () {
                axios.get("/brand/delete.do?ids=" + this.ids).then(function (response) {
                    if (response.data.success) {
                        //刷新数据
                        app.findPage(app.pageNo);
                        //清除已删除的ids
                        app.ids = [];
                    } else {
                        alert(response.data.message);
                    }
                })
            }
        },

        //初始调用入口
        created: function () {
            //this.findAll();

            //初始化查询第一页
            this.findPage(1);
        }
    });
}