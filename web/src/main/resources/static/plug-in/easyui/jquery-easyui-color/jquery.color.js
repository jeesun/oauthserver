(function($){
	$(function(){
		if (!$('#easyui-color-style').length){
			$('head').append(
				'<style id="easyui-color-style">' +
				'.color-cell{display:inline-block;float:left;cursor:pointer;border:1px solid #fff}' +
				'.color-cell:hover{border:1px solid #000}' +
				'</style>'
			);
		}
	});

	function create(target){
		var opts = $.data(target, 'color').options;
		$(target).combo($.extend({}, opts, {
			panelWidth: opts.cellWidth*8+2,
			panelHeight: opts.cellHeight*7+2,
			onShowPanel: function(){
				var p = $(this).combo('panel');
				if (p.is(':empty')){
					var colors = [
						"0,0,0","68,68,68","102,102,102","153,153,153","204,204,204","238,238,238","243,243,243","255,255,255",
						"244,204,204","252,229,205","255,242,204","217,234,211","208,224,227","207,226,243","217,210,233","234,209,220",
						"234,153,153","249,203,156","255,229,153","182,215,168","162,196,201","159,197,232","180,167,214","213,166,189",
						"224,102,102","246,178,107","255,217,102","147,196,125","118,165,175","111,168,220","142,124,195","194,123,160",
						"204,0,0","230,145,56","241,194,50","106,168,79","69,129,142","61,133,198","103,78,167","166,77,121",
						"153,0,0","180,95,6","191,144,0","56,118,29","19,79,92","11,83,148","53,28,117","116,27,71",
						"102,0,0","120,63,4","127,96,0","39,78,19","12,52,61","7,55,99","32,18,77","76,17,48"
					];
					for(var i=0; i<colors.length; i++){
						var a = $('<a class="color-cell"></a>').appendTo(p);
						a.css('backgroundColor', 'rgb('+colors[i]+')');
					}
					var cells = p.find('.color-cell');
					cells._outerWidth(opts.cellWidth)._outerHeight(opts.cellHeight);
					cells.bind('click.color', function(e){
						var color = $(this).css('backgroundColor');
						$(target).color('setValue', color);
						$(target).combo('hidePanel');
					});
				}
			}
		}));
		if (opts.value){
			$(target).color('setValue', opts.value);
		}
	}

	$.fn.color = function(options, param){
		if (typeof options == 'string'){
			var method = $.fn.color.methods[options];
			if (method){
				return method(this, param);
			} else {
				return this.combo(options, param);
			}
		}
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'color');
			if (state){
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'color', {
					options: $.extend({}, $.fn.color.defaults, $.fn.color.parseOptions(this), options)
				});
			}
			create(this);
		});
	};

	$.fn.color.methods = {
		options: function(jq){
			return jq.data('color').options;
		},
		setValue: function(jq, value){
			return jq.each(function(){
				var tb = $(this).combo('textbox').css('backgroundColor', value);
				value = tb.css('backgroundColor');
				if (value.indexOf('rgb') >= 0){
					var bg = value.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
					value = '#' + hex(bg[1]) + hex(bg[2]) + hex(bg[3]);
				}
				$(this).combo('setValue', value).combo('setText', value);

				function hex(x){
					return ('0'+parseInt(x).toString(16)).slice(-2);
				}
			})
		},
		clear: function(jq){
			return jq.each(function(){
				$(this).combo('clear');
				$(this).combo('textbox').css('backgroundColor', '');
			});
		}
	};

	$.fn.color.parseOptions = function(target){
		return $.extend({}, $.fn.combo.parseOptions(target), {

		});
	};

	$.fn.color.defaults = $.extend({}, $.fn.combo.defaults, {
		editable: false,
		cellWidth: 20,
		cellHeight: 20
	});

	$.parser.plugins.push('color');
})(jQuery);
