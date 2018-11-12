/**
 * nth-tabs
 * author:nethuige
 * version:2.0
 */
(function ($) {

    $.fn.nthTabs = function (options) {

        // 插件中的40为默认左边距
        var nthTabs = this;

        var defaults = {
            allowClose: true, // 新建选项卡，是否允许关闭，默认启用
            active: true, // 新建选项卡，是否为活动状态，默认启用
            location: true, //新建选项卡，是否自动定位，默认启用
            fadeIn: true, // 新建选项卡，淡入效果，默认启用
            rollWidth: nthTabs.width() - 120 // 可滚动的区域宽度，120即3个操作按钮的宽度
        };

        var settings = $.extend({}, defaults, options);

        var frameName = 0;

        var template =
            '<div class="page-tabs">' +
            '<a href="#" class="roll-nav roll-nav-left"><span class="fa fa-angle-double-left"></span></a>' +
            '<div class="content-tabs">' +
            '<div class="content-tabs-container">' +
            '<ul class="nav nav-tabs"></ul>' +
            '</div>' +
            '</div>' +
            '<a href="#" class="roll-nav roll-nav-right"><span class="fa fa-angle-double-right"></span></a>' +
            '<div class="dropdown roll-nav right-nav-list">' +
            '<a href="#" class="dropdown-toggle" data-toggle="dropdown">' +
            '<span class="tab-down"></span></a>' +
            '<ul class="dropdown-menu">' +
            '<li><a href="#" class="tab-location">定位当前选项卡</a></li>' +
            '<li><a href="#" class="tab-close-current">关闭当前选项卡</a></li>' +
            '<li role="separator" class="divider"></li>' +
            '<li><a href="#" class="tab-close-other">关闭其他选项卡</a></li>' +
            '<li><a href="#" class="tab-close-all">关闭全部选项卡</a></li>' +
            '<li class="divider"></li>' +
            '<li class="scrollbar-outer tab-list-scrollbar">' +
            '<div class="tab-list-container"><ul class="tab-list"></ul></div>' +
            '</li>' +
            '</ul>' +
            '</div>' +
            '</div>' +
            '<div class="tab-content"></div>';

        // 启用插件
        var run = function(){
            nthTabs.html(template);
            event.onWindowsResize().onTabClose().onTabRollLeft().onTabRollRight().onTabList()
                .onTabCloseOpt().onTabCloseAll().onTabCloseOther().onLocationTab();
            return methods;
        };

        // 方法列表
        var methods = {

            // 获取所有tab宽度
            getAllTabWidth: function () {
                var sum_width = 0;
                nthTabs.find('.nav-tabs li').each(function () {
                    sum_width += parseFloat($(this).width());
                });
                return sum_width;
            },

            // 获取左右滑动步值
            getMarginStep: function () {
                return settings.rollWidth / 2;
            },

            // 获取当前活动状态选项卡ID
            getActiveId: function () {
                return nthTabs.find('li[class="active"]').find("a").attr("href").replace('#', '');
            },

            // 获取所有选项卡
            getTabList: function () {
                var tabList = [];
                nthTabs.find('.nav-tabs li a').each(function () {
                    tabList.push({id: $(this).attr('href'), title: $(this).children('span').html()});
                });
                return tabList;
            },

            // 新建单个选项卡
            addTab: function (options) {
                // nav-tab
                var tab = [];
                var active = options.active == undefined ? settings.active : options.active;
                var allowClose = options.allowClose == undefined ? settings.allowClose : options.allowClose;
                var location = options.location == undefined ? settings.location : options.location;
                var fadeIn = options.fadeIn == undefined ? settings.fadeIn : options.fadeIn;
                var url = options.url == undefined ? "" : options.url;
                tab.push('<li title="' + options.title + '">');
                tab.push('<a href="#' + options.id + '" data-toggle="tab">');
                tab.push('<span>' + options.title + '</span>');
                tab.push('</a>');
                allowClose ? tab.push('<i class="icon nth-icon-close-mini tab-close"></i>') : '';
                tab.push('</li>');
                nthTabs.find(".nav-tabs").append(tab.join(''));
                //tab-content
                var tabContent = [];
                tabContent.push('<div class="tab-pane '+(fadeIn ? 'animation-fade' : '')+'" id="' + options.id + '">');
                if(url.length>0){
                    tabContent.push('<iframe src="'+options.url+'" frameborder="0" scrolling="0" name="iframe-'+frameName+'" class="nth-tabs-frame" width="100%" style="min-height: 600px;" marginheight="0" onload="this.height=document.body.scrollHeight"></iframe>');
                    frameName++;
                }else{
                    tabContent.push('<div class="nth-tabs-content">'+options.content+"</div>");
                }
                tabContent.push('</div>');
                nthTabs.find(".tab-content").append(tabContent.join(''));
                active && this.setActTab(options.id);
                location && this.locationTab(options.id);
                return this;
            },

            //新建多个选项卡
            addTabs: function (tabsOptions) {
                for(var index in tabsOptions){
                    this.addTab(tabsOptions[index]);
                }
                return this;
            },

            // 定位选项卡
            locationTab: function (tabId) {
                tabId = tabId == undefined ? methods.getActiveId() : tabId;
                tabId = tabId.indexOf('#') > -1 ? tabId : '#' + tabId;
                var navTabOpt = nthTabs.find("[href='" + tabId + "']"); // 当前所操作选项卡对象
                // 计算存在于当前活动选项卡之前的所有同级选项卡的宽度之和
                var beforeTabsWidth = 0;
                navTabOpt.parent().prevAll().each(function () {
                    beforeTabsWidth += $(this).width();
                });
                // 得到选项卡容器对象
                var contentTab = navTabOpt.parent().parent().parent();
                // 情况1：前面同级选项卡宽度之和小于选项卡可视区域的则默认40
                if (beforeTabsWidth <= settings.rollWidth) {
                    margin_left_total = 40;
                }
                // 情况2：前面同级选项卡宽度之和大于选项卡可视区域的，则margin为向左偏移整数倍的距离
                else{
                    margin_left_total = 40 - Math.floor(beforeTabsWidth / settings.rollWidth) * settings.rollWidth;
                }
                contentTab.css("margin-left", margin_left_total);
                return this;
            },

            // 删除单个选项卡
            delTab: function (tabId) {
                tabId = tabId == undefined ? methods.getActiveId() : tabId;
                tabId = tabId.indexOf('#') > -1 ? tabId : '#' + tabId;
                var navTabA = nthTabs.find("[href='" + tabId + "']");
                // 如果关闭的是激活状态的选项卡
                if (navTabA.parent().attr('class') == 'active') {
                    // 激活选项卡，如果后面存在激活后面，否则激活前面
                    var activeNavTab = navTabA.parent().next();
                    var activeTabContent = $(tabId).next();
                    if (activeNavTab.length < 1) {
                        activeNavTab = navTabA.parent().prev();
                        activeTabContent = $(tabId).prev();
                    }
                    activeNavTab.addClass('active');
                    activeTabContent.addClass('active');
                }
                // 移除旧选项卡
                navTabA.parent().remove();
                $(tabId).remove();
                return this;
            },

            // 删除其他选项卡
            delOtherTab: function () {
                nthTabs.find(".nav-tabs li").not('[class="active"]').remove();
                nthTabs.find(".tab-content div.tab-pane").not('[class$="active"]').remove();
                nthTabs.find('.content-tabs-container').css("margin-left", 40); //重置位置
                return this;
            },

            // 删除全部选项卡
            delAllTab: function () {
                nthTabs.find(".nav-tabs li").remove();
                nthTabs.find(".tab-content div").remove();
                return this;
            },

            // 设置活动选项卡
            setActTab: function (tabId) {
                tabId = tabId == undefined ? methods.getActiveId() : tabId;
                tabId = tabId.indexOf('#') > -1 ? tabId : '#' + tabId;
                nthTabs.find('.active').removeClass('active');
                nthTabs.find("[href='" + tabId + "']").parent().addClass('active');
                nthTabs.find(tabId).addClass('active');
                return this;
            },

            // 切换选项卡
            toggleTab: function (tabId) {
                this.setActTab(tabId).locationTab(tabId);
                return this;
            }
        };

        // 事件处理
        var event = {

            // 窗口变化
            onWindowsResize: function () {
                $(window).resize(function () {
                    settings.rollWidth = nthTabs.width() - 120;
                });
                return this;
            },
            
            // 定位选项卡
            onLocationTab: function () {
                nthTabs.on("click", '.tab-location', function () {
                    methods.locationTab();
                });
                return this;
            },

            // 关闭选项卡按钮
            onTabClose: function () {
                nthTabs.on("click", '.tab-close', function () {
                    var tabId = $(this).parent().find("a").attr('href');
                    //当前操作的标签宽度
                    var navTabOpt = nthTabs.find("[href='" + tabId + "']"); // 当前操作选项卡对象
                    // 当前选项卡后有选项卡则不处理，如果无，则整体向左偏移一个选项卡
                    if(navTabOpt.parent().next().length == 0){
                        // 计算存在于当前操作选项卡之前的所有同级选项卡的宽度之和
                        var beforeTabsWidth = 0;
                        navTabOpt.parent().prevAll().each(function () {
                            beforeTabsWidth += $(this).width();
                        });
                        //当前操作选项卡的宽度
                        var optTabWidth = navTabOpt.parent().width();
                        var margin_left_total = 40; // 默认偏移（总宽度未超过滚动区域）
                        // 得到选项卡容器对象
                        var contentTab = navTabOpt.parent().parent().parent();
                        // 满足此情况才需要做整体左偏移处理
                        if (beforeTabsWidth > settings.rollWidth) {
                            var margin_left_origin = contentTab.css('marginLeft').replace('px', '');
                            margin_left_total = parseFloat(margin_left_origin) + optTabWidth + 40;
                        }
                        contentTab.css("margin-left", margin_left_total);
                    }
                    methods.delTab(tabId);
                });
                return this;
            },

            // 关闭当前选项卡操作
            onTabCloseOpt: function () {
                nthTabs.on("click", '.tab-close-current', function () {
                    methods.delTab();
                });
                return this;
            },

            // 关闭其他选项卡
            onTabCloseOther: function () {
                nthTabs.on("click", '.tab-close-other', function () {
                    methods.delOtherTab();
                });
                return this;
            },

            // 关闭全部选项卡
            onTabCloseAll: function () {
                nthTabs.on("click", '.tab-close-all', function () {
                    methods.delAllTab();
                });
                return this;
            },

            // 左滑选项卡
            onTabRollLeft: function () {
                nthTabs.on("click", '.roll-nav-left', function () {
                    var contentTab = $(this).parent().find('.content-tabs-container');
                    var margin_left_total;
                    if (methods.getAllTabWidth() <= settings.rollWidth) {
                        //未超出可视区域宽度,不可滑动
                        margin_left_total = 40;
                    }else{
                        var margin_left_origin = contentTab.css('marginLeft').replace('px', '');
                        margin_left_total = parseFloat(margin_left_origin) + methods.getMarginStep() + 40;
                    }
                    contentTab.css("margin-left", margin_left_total > 40 ? 40 : margin_left_total);
                });
                return this;
            },

            // 右滑选项卡
            onTabRollRight: function () {
                nthTabs.on("click", '.roll-nav-right', function () {
                    if (methods.getAllTabWidth() <= settings.rollWidth) return false; //未超出可视区域宽度,不可滑动
                    var contentTab = $(this).parent().find('.content-tabs-container');
                    var margin_left_origin = contentTab.css('marginLeft').replace('px', '');
                    var margin_left_total = parseFloat(margin_left_origin) - methods.getMarginStep();
                    if (methods.getAllTabWidth() - Math.abs(margin_left_origin) <= settings.rollWidth) return false; //已无隐藏无需滚动
                    contentTab.css("margin-left", margin_left_total);
                });
                return this;
            },

            // 选项卡清单
            onTabList: function () {
                nthTabs.on("click", '.right-nav-list', function () {
                    var tabList = methods.getTabList();
                    var html = [];
                    $.each(tabList, function (key, val) {
                        html.push('<li class="toggle-tab" data-id="' + val.id + '">' + val.title + '</li>');
                    });
                    nthTabs.find(".tab-list").html(html.join(''));
                });
                nthTabs.find(".tab-list-scrollbar").scrollbar();
                this.onTabListToggle();
                return this;
            },

            // 清单下切换选项卡
            onTabListToggle: function () {
                nthTabs.on("click", '.toggle-tab', function () {
                    var tabId = $(this).data("id");
                    methods.setActTab(tabId).locationTab(tabId);
                });
                return this;
            }
        };
        return run();
    }
})(jQuery);