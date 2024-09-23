// import ImageCompressor from 'image-compressor.js';

// export function compressFile(file) {
//   return new Promise((resolve, reject) => {
//     const options = {
//       success(result) {
//         // 将压缩后的 Blob 转换为 File 对象（如果组件支持Blob则不用这一步）
//         const compressedFile = new File([result], file.name, {
//           type: file.type,
//           lastModified: Date.now(),
//         });
//         resolve(compressedFile);
//       },
//       error(e) {
//         reject(e);
//       },
//     };
//     if (file.size > 5 * 1024 * 1024) {
//       options.quality = 0.6; // 压缩质量
//       options.convertSize = false;//不进行图像尺寸的调整
//       options.checkOrientation = false; // 图片翻转，默认为false
//     }
//     new ImageCompressor(file, options);
//   });
// }

