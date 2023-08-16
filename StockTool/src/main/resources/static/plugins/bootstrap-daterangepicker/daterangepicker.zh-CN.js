;(function($){
	$.fn.daterangepicker.defaultOptions = {
			autoApply : true,// 自动提交
			autoUpdateInput : true,// 不自动更新选择的日期
			showDropdowns : false,
			locale : {
				direction : 'ltr',
				format : 'YYYY-MM-DD',
				separator : ' / ',
				applyLabel : '确定',
				cancelLabel : '取消',
				fromLabel : '起始时间',
				toLabel : '结束时间',
				daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
				monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
						'九月', '十月', '十一月', '十二月' ],
				firstDay : 1
			}
	};
}(jQuery));