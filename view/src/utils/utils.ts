import { baseUrl } from "@/utils/service";
import { useStore } from "@/stores/store";

/** 格式化时间 */
export function formatDate(date: Date, format = 'YYYY-MM-DD HH:mm:ss') {
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
/**
 * 将输入值转换为带单位的字符串。
 * @param value - 输入值，可以是数字或字符串。
 * @returns 如果输入已经包含单位，返回原字符串；如果是数字或不带单位的字符串，则返回带有 'px' 单位的字符串。
 */
export function formatValue(value: number | string): string {
    // 正则表达式匹配结尾为单位的情况（如'px', '%', 'em', 'rem', 'vh', 'vw', 等等）
    const unitPattern = /[a-zA-Z%]+$/;
    // 如果值是字符串并且已经包含单位，直接返回
    if (typeof value === 'string' && unitPattern.test(value)) {
        return value;
    }
    // 否则，将数字或不带单位的字符串转换为带有 'px' 的字符串
    return `${value}px`;
}
/** 
 * 传图片名称返回图片在服务器的地址
 * @param fileName - 图片名称
 */
export const getImgUrl = (fileName: string) => {
    return baseUrl + '/file/download-avatar/' + fileName;
};
/** 
 * 上传用户头像
 * @param avatarUrl - 头像图片的本地路径,或者微信的临时
 */
export function uploadAvatar(avatarUrl: string) {
    uni.uploadFile({
        url: `${baseUrl}/file/upload-avatar`,
        filePath: avatarUrl,
        name: "file",
        formData: {
            userId: useStore().user.userId,
        },
        success(res) {
            let data = JSON.parse(res.data) as Quiz.Result<Quiz.UserDto>;
            // console.log(222, "成功", data);
            useStore().$patch(store=>{store.user.avatarUrl = data.data.avatarUrl});
        },
        fail(e) {
            // console.log(222, "失败", e);
        },
        complete(e) {
            // console.log(333, e);
        },
    });
}