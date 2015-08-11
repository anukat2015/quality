/* CVS $Id: $ */
package de.unibonn.iai.eis.diachron.semantics.knownvocabs; 
import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from http://rdfs.org/sioc/ns.rdf 
 * @author Auto-generated by schemagen on 05 Aug 2015 18:09 
 */
public class SIOC {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://rdfs.org/sioc/ns#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    /** <p>The ontology's owl:versionInfo as a string</p> */
    public static final String VERSION_INFO = "Revision: 1.35";
    
    /** <p>Specifies that this Item is about a particular resource, e.g. a Post describing 
     *  a book, hotel, etc.</p>
     */
    public static final Property about = m_model.createProperty( "http://rdfs.org/sioc/ns#about" );
    
    /** <p>Refers to the foaf:Agent or foaf:Person who owns this sioc:UserAccount.</p> */
    public static final Property account_of = m_model.createProperty( "http://rdfs.org/sioc/ns#account_of" );
    
    /** <p>Refers to who (e.g. a UserAccount, e-mail address, etc.) a particular Item 
     *  is addressed to.</p>
     */
    public static final Property addressed_to = m_model.createProperty( "http://rdfs.org/sioc/ns#addressed_to" );
    
    /** <p>A Site that the UserAccount is an administrator of.</p> */
    public static final Property administrator_of = m_model.createProperty( "http://rdfs.org/sioc/ns#administrator_of" );
    
    /** <p>The URI of a file attached to an Item.</p> */
    public static final Property attachment = m_model.createProperty( "http://rdfs.org/sioc/ns#attachment" );
    
    /** <p>An image or depiction used to represent this UserAccount.</p> */
    public static final Property avatar = m_model.createProperty( "http://rdfs.org/sioc/ns#avatar" );
    
    /** <p>An Item that this Container contains.</p> */
    public static final Property container_of = m_model.createProperty( "http://rdfs.org/sioc/ns#container_of" );
    
    /** <p>The content of the Item in plain text format.</p> */
    public static final Property content = m_model.createProperty( "http://rdfs.org/sioc/ns#content" );
    
    /** <p>The encoded content of the Post, contained in CDATA areas.</p> */
    public static final Property content_encoded = m_model.createProperty( "http://rdfs.org/sioc/ns#content_encoded" );
    
    /** <p>When this was created, in ISO 8601 format.</p> */
    public static final Property created_at = m_model.createProperty( "http://rdfs.org/sioc/ns#created_at" );
    
    /** <p>A resource that the UserAccount is a creator of.</p> */
    public static final Property creator_of = m_model.createProperty( "http://rdfs.org/sioc/ns#creator_of" );
    
    /** <p>The content of the Post.</p> */
    public static final Property description = m_model.createProperty( "http://rdfs.org/sioc/ns#description" );
    
    /** <p>Links to a previous (older) revision of this Item or Post.</p> */
    public static final Property earlier_version = m_model.createProperty( "http://rdfs.org/sioc/ns#earlier_version" );
    
    /** <p>An electronic mail address of the UserAccount.</p> */
    public static final Property email = m_model.createProperty( "http://rdfs.org/sioc/ns#email" );
    
    /** <p>An electronic mail address of the UserAccount, encoded using SHA1.</p> */
    public static final Property email_sha1 = m_model.createProperty( "http://rdfs.org/sioc/ns#email_sha1" );
    
    /** <p>This links Items to embedded statements, facts and structured content.</p> */
    public static final Property embeds_knowledge = m_model.createProperty( "http://rdfs.org/sioc/ns#embeds_knowledge" );
    
    /** <p>A feed (e.g. RSS, Atom, etc.) pertaining to this resource (e.g. for a Forum, 
     *  Site, UserAccount, etc.).</p>
     */
    public static final Property feed = m_model.createProperty( "http://rdfs.org/sioc/ns#feed" );
    
    /** <p>First (real) name of this User. Synonyms include given name or christian name.</p> */
    public static final Property first_name = m_model.createProperty( "http://rdfs.org/sioc/ns#first_name" );
    
    /** <p>Indicates that one UserAccount follows another UserAccount (e.g. for microblog 
     *  posts or other content item updates).</p>
     */
    public static final Property follows = m_model.createProperty( "http://rdfs.org/sioc/ns#follows" );
    
    /** <p>A UserAccount that has this Role.</p> */
    public static final Property function_of = m_model.createProperty( "http://rdfs.org/sioc/ns#function_of" );
    
    public static final Property group_of = m_model.createProperty( "http://rdfs.org/sioc/ns#group_of" );
    
    /** <p>A UserAccount that is an administrator of this Site.</p> */
    public static final Property has_administrator = m_model.createProperty( "http://rdfs.org/sioc/ns#has_administrator" );
    
    /** <p>The Container to which this Item belongs.</p> */
    public static final Property has_container = m_model.createProperty( "http://rdfs.org/sioc/ns#has_container" );
    
    /** <p>This is the UserAccount that made this resource.</p> */
    public static final Property has_creator = m_model.createProperty( "http://rdfs.org/sioc/ns#has_creator" );
    
    /** <p>The discussion that is related to this Item.</p> */
    public static final Property has_discussion = m_model.createProperty( "http://rdfs.org/sioc/ns#has_discussion" );
    
    /** <p>A Role that this UserAccount has.</p> */
    public static final Property has_function = m_model.createProperty( "http://rdfs.org/sioc/ns#has_function" );
    
    public static final Property has_group = m_model.createProperty( "http://rdfs.org/sioc/ns#has_group" );
    
    /** <p>The Site that hosts this Forum.</p> */
    public static final Property has_host = m_model.createProperty( "http://rdfs.org/sioc/ns#has_host" );
    
    /** <p>A UserAccount that is a member of this Usergroup.</p> */
    public static final Property has_member = m_model.createProperty( "http://rdfs.org/sioc/ns#has_member" );
    
    /** <p>A UserAccount that is a moderator of this Forum.</p> */
    public static final Property has_moderator = m_model.createProperty( "http://rdfs.org/sioc/ns#has_moderator" );
    
    /** <p>A UserAccount that modified this Item.</p> */
    public static final Property has_modifier = m_model.createProperty( "http://rdfs.org/sioc/ns#has_modifier" );
    
    /** <p>A UserAccount that this resource is owned by.</p> */
    public static final Property has_owner = m_model.createProperty( "http://rdfs.org/sioc/ns#has_owner" );
    
    /** <p>A Container or Forum that this Container or Forum is a child of.</p> */
    public static final Property has_parent = m_model.createProperty( "http://rdfs.org/sioc/ns#has_parent" );
    
    /** <p>An resource that is a part of this subject.</p> */
    public static final Property has_part = m_model.createProperty( "http://rdfs.org/sioc/ns#has_part" );
    
    /** <p>Points to an Item or Post that is a reply or response to this Item or Post.</p> */
    public static final Property has_reply = m_model.createProperty( "http://rdfs.org/sioc/ns#has_reply" );
    
    /** <p>A resource that this Role applies to.</p> */
    public static final Property has_scope = m_model.createProperty( "http://rdfs.org/sioc/ns#has_scope" );
    
    /** <p>A data Space which this resource is a part of.</p> */
    public static final Property has_space = m_model.createProperty( "http://rdfs.org/sioc/ns#has_space" );
    
    /** <p>A UserAccount that is subscribed to this Container.</p> */
    public static final Property has_subscriber = m_model.createProperty( "http://rdfs.org/sioc/ns#has_subscriber" );
    
    /** <p>Points to a Usergroup that has certain access to this Space.</p> */
    public static final Property has_usergroup = m_model.createProperty( "http://rdfs.org/sioc/ns#has_usergroup" );
    
    /** <p>A Forum that is hosted on this Site.</p> */
    public static final Property host_of = m_model.createProperty( "http://rdfs.org/sioc/ns#host_of" );
    
    /** <p>An identifier of a SIOC concept instance. For example, a user ID. Must be 
     *  unique for instances of each type of SIOC concept within the same site.</p>
     */
    public static final Property id = m_model.createProperty( "http://rdfs.org/sioc/ns#id" );
    
    /** <p>The IP address used when creating this Item. This can be associated with a 
     *  creator. Some wiki articles list the IP addresses for the creator or modifiers 
     *  when the usernames are absent.</p>
     */
    public static final Property ip_address = m_model.createProperty( "http://rdfs.org/sioc/ns#ip_address" );
    
    /** <p>The date and time of the last activity associated with a SIOC concept instance, 
     *  and expressed in ISO 8601 format. This could be due to a reply Post or Comment, 
     *  a modification to an Item, etc.</p>
     */
    public static final Property last_activity_date = m_model.createProperty( "http://rdfs.org/sioc/ns#last_activity_date" );
    
    /** <p>The date and time of the last Post (or Item) in a Forum (or a Container), 
     *  in ISO 8601 format.</p>
     */
    public static final Property last_item_date = m_model.createProperty( "http://rdfs.org/sioc/ns#last_item_date" );
    
    /** <p>Last (real) name of this user. Synonyms include surname or family name.</p> */
    public static final Property last_name = m_model.createProperty( "http://rdfs.org/sioc/ns#last_name" );
    
    /** <p>The date and time of the last reply Post or Comment, which could be associated 
     *  with a starter Item or Post or with a Thread, and expressed in ISO 8601 format.</p>
     */
    public static final Property last_reply_date = m_model.createProperty( "http://rdfs.org/sioc/ns#last_reply_date" );
    
    /** <p>Links to a later (newer) revision of this Item or Post.</p> */
    public static final Property later_version = m_model.createProperty( "http://rdfs.org/sioc/ns#later_version" );
    
    /** <p>Links to the latest revision of this Item or Post.</p> */
    public static final Property latest_version = m_model.createProperty( "http://rdfs.org/sioc/ns#latest_version" );
    
    /** <p>A URI of a document which contains this SIOC object.</p> */
    public static final Property link = m_model.createProperty( "http://rdfs.org/sioc/ns#link" );
    
    /** <p>Links extracted from hyperlinks within a SIOC concept, e.g. Post or Site.</p> */
    public static final Property links_to = m_model.createProperty( "http://rdfs.org/sioc/ns#links_to" );
    
    /** <p>A Usergroup that this UserAccount is a member of.</p> */
    public static final Property member_of = m_model.createProperty( "http://rdfs.org/sioc/ns#member_of" );
    
    /** <p>A Forum that a UserAccount is a moderator of.</p> */
    public static final Property moderator_of = m_model.createProperty( "http://rdfs.org/sioc/ns#moderator_of" );
    
    /** <p>When this was modified, in ISO 8601 format.</p> */
    public static final Property modified_at = m_model.createProperty( "http://rdfs.org/sioc/ns#modified_at" );
    
    /** <p>An Item that this UserAccount has modified.</p> */
    public static final Property modifier_of = m_model.createProperty( "http://rdfs.org/sioc/ns#modifier_of" );
    
    /** <p>The name of a SIOC concept instance, e.g. a username for a UserAccount, group 
     *  name for a Usergroup, etc.</p>
     */
    public static final Property name = m_model.createProperty( "http://rdfs.org/sioc/ns#name" );
    
    /** <p>Next Item or Post in a given Container sorted by date.</p> */
    public static final Property next_by_date = m_model.createProperty( "http://rdfs.org/sioc/ns#next_by_date" );
    
    /** <p>Links to the next revision of this Item or Post.</p> */
    public static final Property next_version = m_model.createProperty( "http://rdfs.org/sioc/ns#next_version" );
    
    /** <p>A note associated with this resource, for example, if it has been edited by 
     *  a UserAccount.</p>
     */
    public static final Property note = m_model.createProperty( "http://rdfs.org/sioc/ns#note" );
    
    /** <p>The number of unique authors (UserAccounts and unregistered posters) who have 
     *  contributed to this Item, Thread, Post, etc.</p>
     */
    public static final Property num_authors = m_model.createProperty( "http://rdfs.org/sioc/ns#num_authors" );
    
    /** <p>The number of Posts (or Items) in a Forum (or a Container).</p> */
    public static final Property num_items = m_model.createProperty( "http://rdfs.org/sioc/ns#num_items" );
    
    /** <p>The number of replies that this Item, Thread, Post, etc. has. Useful for when 
     *  the reply structure is absent.</p>
     */
    public static final Property num_replies = m_model.createProperty( "http://rdfs.org/sioc/ns#num_replies" );
    
    /** <p>The number of Threads (AKA discussion topics) in a Forum.</p> */
    public static final Property num_threads = m_model.createProperty( "http://rdfs.org/sioc/ns#num_threads" );
    
    /** <p>The number of times this Item, Thread, UserAccount profile, etc. has been 
     *  viewed.</p>
     */
    public static final Property num_views = m_model.createProperty( "http://rdfs.org/sioc/ns#num_views" );
    
    /** <p>A resource owned by a particular UserAccount, for example, a weblog or image 
     *  gallery.</p>
     */
    public static final Property owner_of = m_model.createProperty( "http://rdfs.org/sioc/ns#owner_of" );
    
    /** <p>A child Container or Forum that this Container or Forum is a parent of.</p> */
    public static final Property parent_of = m_model.createProperty( "http://rdfs.org/sioc/ns#parent_of" );
    
    /** <p>A resource that the subject is a part of.</p> */
    public static final Property part_of = m_model.createProperty( "http://rdfs.org/sioc/ns#part_of" );
    
    /** <p>Previous Item or Post in a given Container sorted by date.</p> */
    public static final Property previous_by_date = m_model.createProperty( "http://rdfs.org/sioc/ns#previous_by_date" );
    
    /** <p>Links to the previous revision of this Item or Post.</p> */
    public static final Property previous_version = m_model.createProperty( "http://rdfs.org/sioc/ns#previous_version" );
    
    /** <p>Links either created explicitly or extracted implicitly on the HTML level 
     *  from the Post.</p>
     */
    public static final Property reference = m_model.createProperty( "http://rdfs.org/sioc/ns#reference" );
    
    /** <p>Related Posts for this Post, perhaps determined implicitly from topics or 
     *  references.</p>
     */
    public static final Property related_to = m_model.createProperty( "http://rdfs.org/sioc/ns#related_to" );
    
    /** <p>Links to an Item or Post which this Item or Post is a reply to.</p> */
    public static final Property reply_of = m_model.createProperty( "http://rdfs.org/sioc/ns#reply_of" );
    
    /** <p>A Role that has a scope of this resource.</p> */
    public static final Property scope_of = m_model.createProperty( "http://rdfs.org/sioc/ns#scope_of" );
    
    /** <p>An Item may have a sibling or a twin that exists in a different Container, 
     *  but the siblings may differ in some small way (for example, language, category, 
     *  etc.). The sibling of this Item should be self-describing (that is, it should 
     *  contain all available information).</p>
     */
    public static final Property sibling = m_model.createProperty( "http://rdfs.org/sioc/ns#sibling" );
    
    /** <p>A resource which belongs to this data Space.</p> */
    public static final Property space_of = m_model.createProperty( "http://rdfs.org/sioc/ns#space_of" );
    
    /** <p>Keyword(s) describing subject of the Post.</p> */
    public static final Property subject = m_model.createProperty( "http://rdfs.org/sioc/ns#subject" );
    
    /** <p>A Container that a UserAccount is subscribed to.</p> */
    public static final Property subscriber_of = m_model.createProperty( "http://rdfs.org/sioc/ns#subscriber_of" );
    
    /** <p>This is the title (subject line) of the Post. Note that for a Post within 
     *  a threaded discussion that has no parents, it would detail the topic thread.</p>
     */
    public static final Property title = m_model.createProperty( "http://rdfs.org/sioc/ns#title" );
    
    /** <p>A topic of interest, linking to the appropriate URI, e.g. in the Open Directory 
     *  Project or of a SKOS category.</p>
     */
    public static final Property topic = m_model.createProperty( "http://rdfs.org/sioc/ns#topic" );
    
    /** <p>A Space that the Usergroup has access to.</p> */
    public static final Property usergroup_of = m_model.createProperty( "http://rdfs.org/sioc/ns#usergroup_of" );
    
    /** <p>Community is a high-level concept that defines an online community and what 
     *  it consists of.</p>
     */
    public static final Resource Community = m_model.createResource( "http://rdfs.org/sioc/ns#Community" );
    
    /** <p>An area in which content Items are contained.</p> */
    public static final Resource Container = m_model.createResource( "http://rdfs.org/sioc/ns#Container" );
    
    /** <p>A discussion area on which Posts or entries are made.</p> */
    public static final Resource Forum = m_model.createResource( "http://rdfs.org/sioc/ns#Forum" );
    
    /** <p>An Item is something which can be in a Container.</p> */
    public static final Resource Item = m_model.createResource( "http://rdfs.org/sioc/ns#Item" );
    
    /** <p>An article or message that can be posted to a Forum.</p> */
    public static final Resource Post = m_model.createResource( "http://rdfs.org/sioc/ns#Post" );
    
    /** <p>A Role is a function of a UserAccount within a scope of a particular Forum, 
     *  Site, etc.</p>
     */
    public static final Resource Role = m_model.createResource( "http://rdfs.org/sioc/ns#Role" );
    
    /** <p>A Site can be the location of an online community or set of communities, with 
     *  UserAccounts and Usergroups creating Items in a set of Containers. It can 
     *  be thought of as a web-accessible data Space.</p>
     */
    public static final Resource Site = m_model.createResource( "http://rdfs.org/sioc/ns#Site" );
    
    /** <p>A Space is a place where data resides, e.g. on a website, desktop, fileshare, 
     *  etc.</p>
     */
    public static final Resource Space = m_model.createResource( "http://rdfs.org/sioc/ns#Space" );
    
    /** <p>A container for a series of threaded discussion Posts or Items.</p> */
    public static final Resource Thread = m_model.createResource( "http://rdfs.org/sioc/ns#Thread" );
    
    /** <p>UserAccount is now preferred. This is a deprecated class for a User in an 
     *  online community site.</p>
     */
    public static final Resource User = m_model.createResource( "http://rdfs.org/sioc/ns#User" );
    
    /** <p>A user account in an online community site.</p> */
    public static final Resource UserAccount = m_model.createResource( "http://rdfs.org/sioc/ns#UserAccount" );
    
    /** <p>A set of UserAccounts whose owners have a common purpose or interest. Can 
     *  be used for access control purposes.</p>
     */
    public static final Resource Usergroup = m_model.createResource( "http://rdfs.org/sioc/ns#Usergroup" );
    
}
