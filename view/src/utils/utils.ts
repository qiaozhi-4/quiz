/** 格式化时间 */
export function formatDate(date : Date, format = 'YYYY-MM-DD HH:mm:ss') {
	// 获取年、月、日、小时、分钟、秒
	const year = date.getFullYear().toString();  // 获取年份
	const month = String(date.getMonth() + 1).padStart(2, '0'); // 获取月份，补全两位数
	const day = String(date.getDate()).padStart(2, '0'); // 获取日期，补全两位数
	const hours = String(date.getHours()).padStart(2, '0'); // 获取小时，补全两位数
	const minutes = String(date.getMinutes()).padStart(2, '0'); // 获取分钟，补全两位数
	const seconds = String(date.getSeconds()).padStart(2, '0'); // 获取秒，补全两位数

	// 使用正则表达式替换格式化字符串中的占位符
	const formattedDate = format
		.replace('YYYY', year)
		.replace('MM', month)
		.replace('DD', day)
		.replace('HH', hours)
		.replace('mm', minutes)
		.replace('ss', seconds);
	return formattedDate; // 返回格式化后的字符串
}
/** 判断字符串是否是纯数字,如果是就加上单位:'px' */
export function addPxIfNumeric(value : string | number) {
	// 如果值本身就是一个数字类型，直接返回添加 "px"
	if (typeof value === 'number') {
		return value + 'px';
	}
	// 尝试将字符串转换为数字
	const number = parseFloat(value);
	// 判断字符串是否可以转换为有效数字（非NaN且是有限数）
	if (!isNaN(number) && isFinite(number)) {
		return number + 'px';
	} else {
		return value; // 如果不是数字，则返回原始值
	}
}