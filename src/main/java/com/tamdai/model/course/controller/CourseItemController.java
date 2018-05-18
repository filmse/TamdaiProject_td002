package com.tamdai.model.course.controller;

import com.tamdai.model.course.entity.Course;
import com.tamdai.model.course.entity.CourseItem;
import com.tamdai.model.course.entity.ImageItem;
import com.tamdai.model.course.entity.VideoClip;
import com.tamdai.model.course.repository.ImageItemRepository;
import com.tamdai.model.course.repository.VideoClipRepository;
import com.tamdai.model.course.service.CourseService;
import com.tamdai.model.security.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.StandardOpenOption.READ;

/**
 * Created by Film on 21/9/2560.
 */

@CrossOrigin
@RestController
@RequestMapping("/")
public class CourseItemController {

	@Autowired
    CourseService courseService;

	@Autowired
    UserService userService;

	@Autowired
	VideoClipRepository videoClipRepository;

	public static String PathImageitem = "C:\\Users\\Film\\Documents\\Tamdai\\td002\\src\\main\\resources\\imageitem\\";
	//public static String PathImageitem = "C:\\Users\\Film_PC\\Documents\\Projects\\TD002\\src\\main\\resources\\imageitem\\";

	public static String PathVideo = "C:\\Users\\Film\\Documents\\Tamdai\\td002\\src\\main\\resources\\video\\";
	//public static String PathVideo = "C:\\Users\\Film_PC\\Documents\\Projects\\TD002\\src\\main\\resources\\video\\";

	@Autowired
	ImageItemRepository imageItemRepository;

	@RequestMapping(value = "update/ItemDetails/{id}", method = RequestMethod.PUT)
	public CourseItem updateCourseItem(@PathVariable("id") Long id, @RequestParam("name") String name,
                                       @RequestParam("description") String description, @RequestParam("videoPath") String videoPath,
                                       @RequestParam("courseText") String courseText, @RequestParam("videoTime") String videoTime,
                                       @RequestParam("orderItem") String orderItem, @RequestParam("canPreview") String canPreview) {
		CourseItem courseItem = courseService.courseItemId(id);
		courseItem.setName(name);
		courseItem.setDescription(description);
		courseItem.setCanPreview(canPreview);
		courseItem.setVideoPath(videoPath);
		courseItem.setCourseText(courseText);
		courseItem.setVideoTime(videoTime);
		courseItem.setOrderItem(orderItem);
		return courseService.updateCourseItem(courseItem);
	}

	@RequestMapping(value = "create/courseVideoItem", method = RequestMethod.POST)
	public CourseItem createCourseVideoItem(@RequestBody CourseItem courseItem, @RequestParam("courseId") Long id,
                                            BindingResult bindingResult) {
		Course course = courseService.getCourseId(id);
		return courseService.createCourseVideoItem(courseItem, course);
	}

	@RequestMapping(value = "create/courseImageItem", method = RequestMethod.POST)
	public CourseItem createCourseImageItem(@RequestBody CourseItem courseItem, @RequestParam("courseId") Long id,
                                            BindingResult bindingResult) {
		Course course = courseService.getCourseId(id);
		return courseService.createCourseImageItem(courseItem, course);
	}

	@RequestMapping(value = "create/courseTextItem", method = RequestMethod.POST)
	public CourseItem createCourseTextItem(@RequestBody CourseItem courseItem, @RequestParam("courseId") Long id,
                                           BindingResult bindingResult) {
		Course course = courseService.getCourseId(id);
		return courseService.createCourseTextItem(courseItem, course);
	}

	@RequestMapping(value = "get/courseItems", method = RequestMethod.GET)
	public List<CourseItem> list(HttpServletRequest request) throws IOException {
		return courseService.getCourseItemList();
	}

	@RequestMapping(value = "getCourseItemById/{id}", method = RequestMethod.GET)
	public CourseItem getCourseItemById(@PathVariable("id") Long id) {
		return courseService.getCourseItemById(id);
	}

	@RequestMapping(value = "courseItem/{id}", method = RequestMethod.GET)
	public CourseItem courseItemId(@PathVariable("id") Long id) {
		return courseService.courseItemId(id);
	}

	@RequestMapping(value = "add/videoItem", method = RequestMethod.POST)
	public @ResponseBody
    String handleFileUpload(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("id") Long id, @RequestParam("file") MultipartFile file) throws IOException {
		MultipartHttpServletRequest mRequest;
		CourseItem courseItem = courseService.courseItemId(id);

		if (!file.isEmpty()) {
			try {
				mRequest = (MultipartHttpServletRequest) request;
				Iterator<String> itr = mRequest.getFileNames();
				while (itr.hasNext()) {
					MultipartFile multipartFile = mRequest.getFile(itr.next());

					String destination = PathVideo + multipartFile.getOriginalFilename();

					VideoClip video = new VideoClip();
					video.setFileName(multipartFile.getOriginalFilename());
					video.setLessonName(multipartFile.getOriginalFilename());
					video.setContentType(multipartFile.getContentType());

					// setDate
					String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					video.setCreateDate(date);
					video.setPath(destination);
					courseService.saveVideo(video, courseItem);

					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
							new File( PathVideo + multipartFile.getOriginalFilename())));
					stream.write(bytes);
					stream.close();
				}
				return "You successfully uploaded ";
			} catch (Exception e) {
				return "Failed to upload " + " => " + e.getMessage();
			}
		} else {
			return "Failed to upload " + " because the file was empty.";
		}
	}

	@RequestMapping(value = "get/videoList", method = RequestMethod.GET)
	public List<VideoClip> getvideoList(HttpServletRequest request) throws IOException {
		return courseService.getVideoList();
	}

	@RequestMapping(value = "playVideo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void home(@PathVariable("id") Long id, Locale locale, Model model, HttpServletRequest request,
                     HttpServletResponse response) throws IOException {
		VideoClip videoClip = videoClipRepository.getOne(id);

		final int BUFFER_LENGTH = 1024 * 16;
		final long EXPIRE_TIME = 1000 * 60 * 60 * 24;
		final Pattern RANGE_PATTERN = Pattern.compile("bytes=(?<start>\\d*)-(?<end>\\d*)");

		Path vdo = Paths.get( PathVideo + videoClip.getFileName());

		int length = (int) Files.size(vdo);
		int start = 0;
		int end = length - 1;

		String range = request.getHeader("Range");
		Matcher matcher = RANGE_PATTERN.matcher(range);

		if (matcher.matches()) {
			String startGroup = matcher.group("start");
			start = startGroup.isEmpty() ? start : Integer.valueOf(startGroup);
			start = start < 0 ? 0 : start;

			String endGroup = matcher.group("end");
			end = endGroup.isEmpty() ? end : Integer.valueOf(endGroup);
			end = end > length - 1 ? length - 1 : end;
		}

		int contentLength = end - start + 1;
		response.reset();
		response.setBufferSize(BUFFER_LENGTH);
		response.setHeader("Content-Disposition", String.format("inline;filename=\"%s\"", videoClip));
		response.setHeader("Accept-Ranges", "bytes");
		response.setDateHeader("Last-Modified", Files.getLastModifiedTime(vdo).toMillis());
		response.setDateHeader("Expires", System.currentTimeMillis() + EXPIRE_TIME);
		response.setContentType(Files.probeContentType(vdo));
		response.setHeader("Content-Range", String.format("bytes %s-%s/%s", start, end, length));
		response.setHeader("Content-Length", String.format("%s", contentLength));
		response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

		int bytesRead;
		int bytesLeft = contentLength;
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_LENGTH);

		try (SeekableByteChannel input = Files.newByteChannel(vdo, READ);
				OutputStream output = response.getOutputStream()) {

			input.position(start);

			while ((bytesRead = input.read(buffer)) != -1 && bytesLeft > 0) {
				buffer.clear();
				output.write(buffer.array(), 0, bytesLeft < bytesRead ? bytesLeft : bytesRead);
				bytesLeft -= bytesRead;
			}
		}
	}

	@RequestMapping(value = "get/videoCourseList", method = RequestMethod.GET)
	public List<VideoClip> getVideoCourseList(HttpServletRequest request) throws IOException {
		return courseService.getVideoCourseList();
	}

	@RequestMapping(value = "videoClips/{id}", method = RequestMethod.GET)
	public VideoClip getVideoClipsById(@PathVariable("id") Long id) {
		return courseService.getVideoClipsById(id);
	}

	@RequestMapping(value = "add/imageItem", method = RequestMethod.POST)
	@ResponseBody
	public CourseItem addImageItem(HttpServletRequest request, @RequestParam("file") MultipartFile file,
                                   @RequestParam("id") Long id) throws IOException {
		MultipartHttpServletRequest mRequest;
		CourseItem courseItem = courseService.getCourseItemById(id);

		try {
			mRequest = (MultipartHttpServletRequest) request;
			Iterator<String> itr = mRequest.getFileNames();
			while (itr.hasNext()) {
				MultipartFile multipartFile = mRequest.getFile(itr.next());

				String destination = PathImageitem + multipartFile.getOriginalFilename();

				ImageItem imageItem = new ImageItem();
				imageItem.setImageName(multipartFile.getOriginalFilename());
				imageItem.setImageContentType(multipartFile.getContentType());
				imageItem.setPath(destination);
				courseService.saveCourseImageItem(courseItem, imageItem);

				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
						new File( PathImageitem + multipartFile.getOriginalFilename())));
				stream.write(bytes);
				stream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return courseItem;
	}

	@RequestMapping(value = "displayImageItem/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void image(@PathVariable("id") Long id, Locale locale, Model model, HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

		ImageItem imageItem = imageItemRepository.getOne(id);

		String filePath = PathImageitem + imageItem.getImageName();

		int fileSize = (int) new File(filePath).length();
		response.setContentLength(fileSize);
		response.setContentType("image");

		FileInputStream inputStream = new FileInputStream(filePath);
		ServletOutputStream outputStream = response.getOutputStream();
		int value = IOUtils.copy(inputStream, outputStream);
		System.out.println("File Size :: " + fileSize);
		System.out.println("Copied Bytes :: " + value);
		IOUtils.closeQuietly(inputStream);
		IOUtils.closeQuietly(outputStream);
	}

	@RequestMapping(value = "get/imageItemCourseList", method = RequestMethod.GET)
	public List<ImageItem> getImageItemCourseList(HttpServletRequest request) throws IOException {
		return courseService.getImageItemCourseList();
	}

	@RequestMapping(value = "deleteItem", method = RequestMethod.DELETE)
	@ResponseBody
	public Course deleteItem(@RequestParam("courseItem") Long courseItem, @RequestParam("courseId") Long courseId) {
		Course course = courseService.getCourseId(courseId);
		return courseService.deleteItem(course, courseItem);
	}

}
