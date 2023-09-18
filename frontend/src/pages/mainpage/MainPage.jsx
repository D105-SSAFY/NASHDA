import * as s from "./style";

export default function MainPage() {
    return (
        <>
            <s.Header>
                s<s.HeadingOne>MainPage</s.HeadingOne>
            </s.Header>
            <s.Main>
                <s.Section>
                    <s.SectionHeader>
                        <s.SectionHeadingTwo>Links</s.SectionHeadingTwo>
                    </s.SectionHeader>
                    <s.SectionList>
                        <s.SectionListItem>
                            <s.SectionListLink to={"/signin"}>
                                signin
                            </s.SectionListLink>
                        </s.SectionListItem>
                        <s.SectionListItem>
                            <s.SectionListLink to={"/signup"}>
                                signup
                            </s.SectionListLink>
                        </s.SectionListItem>
                    </s.SectionList>
                </s.Section>
            </s.Main>
        </>
    );
}
