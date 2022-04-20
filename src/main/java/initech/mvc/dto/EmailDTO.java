package initech.mvc.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailDTO {
	/**
	 * listReceiverTo
	 */
	private List listReceiverTo = null;

	/**
	 * listReceiverCc
	 */
	private List listReceiverCc = null;

	/**
	 * subject
	 */
	private String subject = null;

	/**
	 * hearderTitle
	 */
	private String hearderTitle = null;

	/**
	 * message
	 */
	private String message = null;

	/**
	 * messageMap
	 */
	private Map messageMap = new HashMap();

	/**
	 * messageOrder
	 */
	private List messageOrder = new ArrayList();

	/**
	 * from
	 */
	private String from = null;

	/**
	 * strReceiverTo
	 */
	private String strReceiverTo = null;

	/**
	 * footerNotice
	 */
	private String footerNotice = null;

	/**
	 * 생성자
	 *
	 */
	public EmailDTO() {
		listReceiverTo = new ArrayList();
		listReceiverCc = new ArrayList();

	}

	/**
	 * <pre>
	 * 메일 수신자 추가
	 * </pre>
	 *
	 * @param sReceiverTo
	 * @return
	 */
	public boolean addReceiverTo(String sReceiverTo) {
		if (listReceiverTo != null) {
			return listReceiverTo.add(sReceiverTo);
		} else {
			log.error("ReceiverTo List is null");
			return false;
		}
	}

	/**
	 * <pre>
	 * 메일 수신자 목록 리턴
	 * </pre>
	 *
	 * @return
	 */
	public List getReceiverTo() {
		if ((this.listReceiverTo == null || this.listReceiverTo.size() <= 0) && this.strReceiverTo != null
				&& this.strReceiverTo.length() > 0) {
			log.debug("strReceiverTo: " + this.strReceiverTo);
			StringTokenizer tokenTo = new StringTokenizer(this.strReceiverTo, ";");
			while (tokenTo.hasMoreTokens()) {
				String addr = tokenTo.nextToken();
				addReceiverTo(addr);
			}
		}
		return listReceiverTo;
	}

	/**
	 * <pre>
	 * 메일 수신자를 String[]로 리턴
	 * </pre>
	 *
	 * @return
	 */
	public String[] getReceiverToByArray() {
		if (this.listReceiverTo == null) {
			return new String[0];
		} else {
			return (String[]) this.listReceiverTo.toArray(new String[this.listReceiverTo.size()]);
		}
	}

	/**
	 * <pre>
	 * 메일 수신자를 ";"를 구분자로 붙여 String으로 리턴한다.
	 * </pre>
	 *
	 * @return
	 */
	public String writeReceiverTo() {
		StringBuffer receivers = new StringBuffer();
		if (this.listReceiverTo != null && this.listReceiverTo.size() > 0) {
			for (int i = 0; i < this.listReceiverTo.size(); i++) {
				String receiver = (String) this.listReceiverTo.get(i);
				receivers.append(receiver).append(";");
			}
		}

		return receivers.toString();
	}

	/**
	 * <pre>
	 * 참조목록에 메일 수신자를 추가한다.
	 * </pre>
	 *
	 * @param sReceiverCc
	 * @return
	 */
	public boolean addReceiverCc(String sReceiverCc) {
		if (listReceiverCc != null) {
			return listReceiverCc.add(sReceiverCc);
		} else {
			log.error("ReceiverCc List is null");
			return false;
		}
	}

	/**
	 * <pre>
	 * ReceiverCc getter
	 * </pre>
	 *
	 * @return
	 */
	public List getReceiverCc() {
		return listReceiverCc;
	}

	/**
	 * <pre>
	 * Subject setter
	 * </pre>
	 *
	 * @param sSubject
	 */
	public void setSubject(String sSubject) {
		this.subject = sSubject;
	}

	/**
	 * <pre>
	 * Subject getter
	 * </pre>
	 *
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * <pre>
	 * Subject의 화면표시
	 * </pre>
	 *
	 * @return
	 */
	public String writeSubject() {
		if (this.subject == null) {
			return "";
		} else {
			return this.subject;
		}
	}

	/**
	 * <pre>
	 * Message setter
	 * </pre>
	 *
	 * @param sMessage
	 */
	public void setMessage(String sMessage) {
		this.message = sMessage;
	}

	/**
	 * <pre>
	 * Message getter
	 * </pre>
	 *
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * <pre>
	 * Message 화면 표시
	 * </pre>
	 *
	 * @return
	 */
	public String writeMessage() {
		if (this.message == null) {
			return null;
		} else {
			return this.message;
		}
	}

	/**
	 * <pre>
	 * 메일의 보낸이 이메일 주소 setter
	 * </pre>
	 *
	 * @param sFrom
	 */
	public void setFrom(String sFrom) {
		this.from = sFrom;
	}

	/**
	 * <pre>
	 * 메일의 보낸이 이메일 주소 getter
	 * </pre>
	 *
	 * @return
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * <pre>
	 * 메일의 보낸이 이메일 주소 화면 표시
	 * </pre>
	 *
	 * @return
	 */
	public String writeFrom() {
		return (this.from == null ? "" : this.from);
	}

	/**
	 * <pre>
	 * ListReceiverTo setter
	 * </pre>
	 *
	 * @param listReceiverTo
	 */
	public void setListReceiverTo(List listReceiverTo) {
		this.listReceiverTo = listReceiverTo;
	}

	/**
	 * @return Returns the hearderTitle.
	 */
	public String getHearderTitle() {
		return hearderTitle;
	}

	/**
	 * @param hearderTitle The hearderTitle to set.
	 */
	public void setHearderTitle(String hearderTitle) {
		this.hearderTitle = hearderTitle;
	}

	/**
	 * <pre>
	 * 표시할 메시지와 타이틀 설정
	 * </pre>
	 *
	 * @param title
	 * @param value
	 */
	public void setMessageMap(String title, String value) {
		this.messageOrder.add(title);
		this.messageMap.put(title, value == null ? "" : value);
	}

	/**
	 * <pre>
	 * 해당 인덱스의 value 리턴
	 * </pre>
	 *
	 * @param index
	 * @return
	 */
	public String getMessageValue(int index) {
		return (String) messageMap.get(this.messageOrder.get(index));
	}

	/**
	 * <pre>
	 * 해당 인덱스의 title 리턴
	 * </pre>
	 *
	 * @param index
	 * @return
	 */
	public String getMessageTitle(int index) {
		return (String) this.messageOrder.get(index);
	}

	/**
	 * <pre>
	 * message map의 개수 리턴
	 * </pre>
	 *
	 * @return
	 */
	public int getMessageMapSize() {
		return this.messageOrder.size();
	}

	/**
	 * @return Returns the footerNotice.
	 */
	public String getFooterNotice() {
		return footerNotice;
	}

	/**
	 * @param footerNotice The footerNotice to set.
	 */
	public void setFooterNotice(String footerNotice) {
		this.footerNotice = footerNotice;
	}
}
