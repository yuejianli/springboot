/**
 * @author zhixin wen <wenzhixin2010@gmail.com>
 * @version: v1.0.1
 */

(function ($) {
    'use strict';

    $.extend($.fn.bootstrapTable.defaults, {
        fixedColumns: false,
        fixedNumber: 1
    });

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _initHeader = BootstrapTable.prototype.initHeader,
        _initBody = BootstrapTable.prototype.initBody,
        _resetView = BootstrapTable.prototype.resetView;

    BootstrapTable.prototype.initFixedColumns = function () {
        this.$fixedHeader = $([
            '<div class="fixed-table-header-columns">',
            '<table>',
            '<thead></thead>',
            '</table>',
            '</div>'].join(''));

        this.timeoutHeaderColumns_ = 0;
        this.$fixedHeader.find('table').attr('class', this.$el.attr('class'));
        this.$fixedHeaderColumns = this.$fixedHeader.find('thead');
        this.$tableHeader.before(this.$fixedHeader);

        this.$fixedBody = $([
            '<div class="fixed-table-body-columns">',
            '<table>',
            '<tbody></tbody>',
            '<tfoot></tfoot>',
            '</table>',
            '</div>'].join(''));

        this.timeoutBodyColumns_ = 0;
        this.$fixedBody.find('table').attr('class', this.$el.attr('class'));
        this.$fixedBodyColumns = this.$fixedBody.find('tbody');
        this.$fixedFooterColumns = this.$fixedBody.find('tfoot');
        this.$tableBody.before(this.$fixedBody);
    };

    BootstrapTable.prototype.initHeader = function () {
        _initHeader.apply(this, Array.prototype.slice.apply(arguments));

        if (!this.options.fixedColumns) {
            return;
        }              	

        this.initFixedColumns();

        var that = this, $trs = this.$header.find('tr').clone();
        
        /****************固定列复选框事件****************/
        var $ths = $trs.find('th');      
        var headTh = $ths.eq(0);
        var input=headTh.find('input'); 
    	var inp=that.$header.find("th[data-field=" + 0 + "] input");
   
        /*********************************************************/
    	
        $trs.each(function () {
            $(this).find('th:gt(' + (that.options.fixedNumber - 1) + ')').remove();
        });
        this.$fixedHeaderColumns.html('').append($trs); 
        
        /**固定表头的全选对象*/        
        var $fixedSelectAll=this.$fixedHeaderColumns.find('[name="btSelectAll"]');      
        $fixedSelectAll.off("click").on("click", function() {
        	/**原表头全选点击*/
        	that.$selectAll.click();
        	
            /*去掉选中样式*/
            that.$body.find('tr').removeClass('unified');
            that.$fixedBody.find('tr').removeClass('unified');
        	
        	/**循环固定列表格复选框*/
        	$.each($(".fixed-table-body-columns").find('[name="btSelectItem"]'),function(idx,item){
        		/**原本是选中状态*/
        		if($(item).is(':checked')){
        			/**固定列不是全选状态*/
        			if(!$fixedSelectAll.is(':checked')){
        				if(!$(item).is(':disabled')){
        					$(item).prop("checked",false);
        				}   

        			}
        		}else{/**原本是未选中状态*/
        			if(!$(item).is(':disabled')){
        				$(item).prop("checked",true);
            			//添加选中样式
                        that.$fixedBody.find('tr[data-index="' + $(item).attr("data-index") + '"]').addClass('unified');
        			} 
        		}
        	})
	    });
    };

    BootstrapTable.prototype.initBody = function () {
        _initBody.apply(this, Array.prototype.slice.apply(arguments));

        if (!this.options.fixedColumns) {
            return;
        }

        var that = this,
            rowspan = 0;

        this.$fixedBodyColumns.html('');
        this.$body.find('> tr[data-index]').each(function () {
            var $tr = $(this).clone(),
                $tds = $tr.find('td');

            $tr.html('');
            /*******固定列操作事件转移及复选框事件*******/
            for (var i = 0; i < that.options.fixedNumber; i++) {
                var indexTd = i;
                var oldTd = $tds.eq(indexTd);
                var fixTd = oldTd.clone();
                var buttons = fixTd.find('a');
                
                //事件转移：冻结列里面的事件转移到实际按钮的事件
                buttons.each(function (key, item) {
                    $(item).click(function () {
                        that.$body.find("tr[data-index=" + $tr.attr('data-index') + "] td:eq(" + indexTd + ") a:eq(" + key + ")").click();
                    });
                });
                if(i==0){
                	var inputs=fixTd.find('input');
                	var index=inputs.data("index");
                	var inp=that.$body.find("tr[data-index=" + index + "] td:eq(" + 0 + ") input[data-index=" + index + "]");               	
                	
                	
                	/**固定表头的全选元素*/
                	var $fixHeadAll=$(".fixed-table-header-columns").find('[name="btSelectAll"]');
                	/**固定表格body点击事件*/
                	inputs.off("click").on("click", function() {
                		inp.click();//同步原表格body点击
                		if($fixHeadAll.is(':checked')){//全选状态  
                			/**
                			 * 全选状态下，表格内复选框一定都是选中状态        
                			 */
                			$fixHeadAll.prop("checked",false);//取消全选    			
                		}else{//非全选状态
                			/**
                			 * 如果所有表格内复选框都是选中状态，设置全选
                			 */ 
                			if($(".fixed-table-body-columns").find('[name="btSelectItem"]:not(:disabled):not(:checked)').length==0){
            					$fixHeadAll.prop("checked",true);//全选
            				}
                		}
                    });               	
                }
                $tr.append(fixTd);
            }
            that.$fixedBodyColumns.append($tr);
            /********************************************************/
        });
        var that = this, $trs = this.$tableBody.find('table tfoot tr').clone();
        $trs.each(function () {
        	$(this).find('th:gt(' + (that.options.fixedNumber - 1) + ')').remove();
        });
        this.$fixedFooterColumns.empty().append($trs); 
    };

    BootstrapTable.prototype.resetView = function () {
        _resetView.apply(this, Array.prototype.slice.apply(arguments));

        if (!this.options.fixedColumns) {
            return;
        }

        clearTimeout(this.timeoutHeaderColumns_);
        this.timeoutHeaderColumns_ = setTimeout($.proxy(this.fitHeaderColumns, this), this.$el.is(':hidden') ? 100 : 0);

        clearTimeout(this.timeoutBodyColumns_);
        this.timeoutBodyColumns_ = setTimeout($.proxy(this.fitBodyColumns, this), this.$el.is(':hidden') ? 100 : 0);
    };

    BootstrapTable.prototype.fitHeaderColumns = function () {
        var that = this,
            visibleFields = this.getVisibleFields(),
            headerWidth = 0;

        this.$body.find('tr:first-child:not(.no-records-found) > *').each(function (i) {
            var $this = $(this),
                index = i;

            if (i >= that.options.fixedNumber) {
                return false;
            }

            if (that.options.detailView && !that.options.cardView) {
                index = i - 1;
            }

            that.$fixedHeader.find('th[data-field="' + visibleFields[index] + '"]')
                .find('.fht-cell').width($this.innerWidth());
            headerWidth += $this.outerWidth();
        });
        this.$fixedHeader.find('th').height(this.$header.find('tr').find('th:first-child').innerHeight());
        this.$fixedHeader.width(headerWidth - 1).show();
    };

    BootstrapTable.prototype.fitBodyColumns = function () {
    	var that = this,
	        top = -(parseInt(this.$el.css('margin-top'))),
	        height = this.$tableBody.height();
    	
	    if(this.$tableContainer.width()<this.$tableBody.find('table').width()){
	    	height -= 16;
	    }
	    
	    if (!this.$body.find('> tr[data-index]').length) {
	        this.$fixedBody.hide();
	        return;
	    }
	    
	    if (!this.options.height) {
	        top = this.$fixedHeader.height();
	        height = this.$tableBody.find('table').height() - this.$fixedHeader.find('table').height();
	    }
	    
	    this.$fixedBody.css({
	    	width: this.$fixedHeader.width()+1,
	    	height: height,
	    	top: top
	    }).show();

        this.$fixedBody.css({
            width: this.$fixedHeader.width()+1,
            height: height,
            top: top
        }).show();

        this.$body.find('> tr').each(function (i) {
            that.$fixedBody.find('tr:eq(' + i + ')').height($(this).height());
        });

        // events
        this.$tableBody.on('scroll', function () {
            that.$fixedBody.find('table').css('top', -$(this).scrollTop());
        });
        this.$body.find('> tr[data-index]').off('hover').hover(function () {
            var index = $(this).data('index');
            that.$fixedBody.find('tr[data-index="' + index + '"]').addClass('hover');
        }, function () {
            var index = $(this).data('index');
            that.$fixedBody.find('tr[data-index="' + index + '"]').removeClass('hover');
        });
        this.$fixedBody.find('tr[data-index]').off('hover').hover(function () {
            var index = $(this).data('index');
            that.$body.find('tr[data-index="' + index + '"]').addClass('hover');
        }, function () {
            var index = $(this).data('index');
            that.$body.find('> tr[data-index="' + index + '"]').removeClass('hover');
        });
        this.$body.find('> tr[data-index]').click(function () {
            var index = $(this).data('index');
            that.$fixedBody.find('tr[data-index!="' + index + '"]').removeClass('unified');
            that.$fixedBody.find('tr[data-index="' + index + '"]').addClass('unified');
            $.each(that.$body.find('tr.selected'),function(ids, items){
            	that.$fixedBody.find('tr[data-index="' + $(this).attr("data-index") + '"]').addClass('unified');
            })
        });
        this.$fixedBody.find('tr[data-index]').click(function () {
            var index = $(this).data('index');
            that.$body.find('tr[data-index!="' + index + '"]').removeClass('unified');
            that.$body.find('tr[data-index="' + index + '"]').addClass('unified');
            that.$fixedBody.find('tr[data-index!="' + index + '"]').removeClass('unified');
            $(this).addClass('unified');
            $.each(that.$body.find('tr.selected'),function(ids, items){
            	that.$fixedBody.find('tr[data-index="' + $(this).attr("data-index") + '"]').addClass('unified');
            })
        });
    };
    
})(jQuery);
